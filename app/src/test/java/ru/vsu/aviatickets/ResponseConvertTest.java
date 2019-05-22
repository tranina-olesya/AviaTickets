package ru.vsu.aviatickets;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.vsu.aviatickets.helpers.DateTestHelper;
import ru.vsu.aviatickets.ticketssearch.models.Agent;
import ru.vsu.aviatickets.ticketssearch.models.Carrier;
import ru.vsu.aviatickets.ticketssearch.models.Flight;
import ru.vsu.aviatickets.ticketssearch.models.Place;
import ru.vsu.aviatickets.ticketssearch.models.PriceLink;
import ru.vsu.aviatickets.ticketssearch.models.Ticket;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.Datum;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.Duration;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.KiwiResponse;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.Route;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.SearchParams;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.Seats;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.Itinerary;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.Leg;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.PricingOption;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.Segment;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerAgent;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerCarrier;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerPlace;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerQuery;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerResponse;
import ru.vsu.aviatickets.ticketssearch.providers.KiwiProviderAPI;
import ru.vsu.aviatickets.ticketssearch.providers.SkyScannerProviderAPI;

import static org.junit.Assert.assertEquals;

public class ResponseConvertTest {

    private List<Trip> trips = new ArrayList<>();

    @Before
    public void fillListTrips() {
        List<Ticket> flightParts;
        Flight flight;
        Place origin = new Place("MOW", "airport", "Москва");
        Place destination = new Place("LON", "airport", "Лондон");
        Date dateOutbound = DateTestHelper.getDateFromString("11:00 20/10/2020");
        Date dateInbound = DateTestHelper.getDateFromString("12:00 20/10/2020");

        flightParts = new ArrayList<>();
        Carrier carrier = new Carrier("AB", "AB", "https://pics.avs.io/120/60/AB.png");
        flightParts.add(new Ticket(carrier, origin, destination, dateOutbound, dateInbound, 60, "19"));
        flight = new Flight(origin, destination, dateOutbound, dateInbound, 60, 1, 0, 0, flightParts);
        List<PriceLink> priceLinks = new ArrayList<>();

        List<Agent> agents = new ArrayList<>();
        agents.add(new Agent("Kiwi.com", "https://s1.apideeplink.com/images/websites/skyp.png"));
        priceLinks.add(new PriceLink(agents, 5000.0, "https://www.aaa.ru"));
        Trip trip = new Trip(flight, null, priceLinks, 5000.0);
        trips.add(trip);
    }

    @Test
    public void responseConvert_shouldConvertKiwiResponse() {
        KiwiResponse kiwiResponse = new KiwiResponse();
        Datum datum = new Datum();

        Seats seats = new Seats();
        seats.setAdults(1);
        seats.setChildren(0);
        seats.setInfants(0);
        seats.setPassengers(1);

        SearchParams searchParams = new SearchParams();
        searchParams.setSeats(seats);
        searchParams.setFlyFromType("airport");
        searchParams.setToType("airport");

        kiwiResponse.setSearchParams(searchParams);

        datum.setPrice(5000.0);
        datum.setDeepLink("https://www.aaa.ru");
        datum.setDuration(new Duration(3600, 0, 3600));

        List<Route> routes = new ArrayList<>();
        Route route = new Route();
        route.setReturn(0);
        route.setAirline("AB");
        route.setOperatingCarrier("AB");
        route.setFlyFrom("MOW");
        route.setCityFrom("Москва");
        route.setFlyTo("LON");
        route.setCityTo("Лондон");
        route.setDTime(DateTestHelper.getLongFromDate(DateTestHelper.getDateFromString("11:00 20/10/2020")));
        route.setATime(DateTestHelper.getLongFromDate(DateTestHelper.getDateFromString("12:00 20/10/2020")));
        route.setFlightNo(19);

        routes.add(route);
        datum.setRoute(routes);
        List<Datum> data = new ArrayList<>();
        data.add(datum);
        kiwiResponse.setData(data);

        KiwiProviderAPI kiwiProviderAPI = new KiwiProviderAPI();
        List<Trip> tripsActual = kiwiProviderAPI.convertResponseToTrip(kiwiResponse);
        assertEquals(trips, tripsActual);
    }

    @Test
    public void responseConvert_shouldConvertSkyScannerResponse() {
        SkyScannerResponse skyScannerResponse = new SkyScannerResponse();

        List<Itinerary> itineraries = new ArrayList<>();
        Itinerary itinerary = new Itinerary();
        itinerary.setOutboundLegId("0");
        List<PricingOption> pricingOptions = new ArrayList<>();
        List<Integer> agentIds = new ArrayList<>();
        agentIds.add(0);
        PricingOption pricingOption = new PricingOption(agentIds, 5000.0, "https://www.aaa.ru");
        pricingOptions.add(pricingOption);
        itinerary.setPricingOptions(pricingOptions);
        itineraries.add(itinerary);
        skyScannerResponse.setItineraries(itineraries);

        skyScannerResponse.setQuery(new SkyScannerQuery(1, 0, 0, null, null, null, true));

        List<Leg> legs = new ArrayList<>();
        Leg leg = new Leg();
        leg.setId("0");
        leg.setArrival(DateTestHelper.getDateFromString("12:00 20/10/2020"));
        leg.setDeparture(DateTestHelper.getDateFromString("11:00 20/10/2020"));
        leg.setDuration(60);
        leg.setOriginStation(0);
        leg.setDestinationStation(1);
        List<Integer> segmentIds = new ArrayList<>();
        segmentIds.add(0);
        leg.setSegmentIds(segmentIds);
        legs.add(leg);
        skyScannerResponse.setLegs(legs);

        List<SkyScannerCarrier> skyScannerCarriers = new ArrayList<>();
        skyScannerCarriers.add(new SkyScannerCarrier(0, "AB", "AB", "https://pics.avs.io/120/60/AB.png"));
        skyScannerResponse.setCarriers(skyScannerCarriers);

        List<SkyScannerPlace> skyScannerPlaces = new ArrayList<>();
        skyScannerPlaces.add(new SkyScannerPlace(0, "MOW", "airport", "Москва"));
        skyScannerPlaces.add(new SkyScannerPlace(1, "LON", "airport", "Лондон"));
        skyScannerResponse.setPlaces(skyScannerPlaces);

        List<Segment> segments = new ArrayList<>();
        Segment segment = new Segment();
        segment.setId(0);
        segment.setDepartureDateTime(DateTestHelper.getDateFromString("11:00 20/10/2020"));
        segment.setArrivalDateTime(DateTestHelper.getDateFromString("12:00 20/10/2020"));
        segment.setCarrier(0);
        segment.setOriginStation(0);
        segment.setDestinationStation(1);
        segment.setDuration(60);
        segment.setFlightNumber("19");
        segments.add(segment);
        skyScannerResponse.setSegments(segments);

        List<SkyScannerAgent> skyScannerAgents = new ArrayList<>();
        skyScannerAgents.add(new SkyScannerAgent(0, "Kiwi.com", "https://s1.apideeplink.com/images/websites/skyp.png"));
        skyScannerResponse.setSkyScannerAgents(skyScannerAgents);

        SkyScannerProviderAPI skyScannerProviderAPI = new SkyScannerProviderAPI();
        List<Trip> actualTrips = skyScannerProviderAPI.convertResponseToTrip(skyScannerResponse);
        assertEquals(trips, actualTrips);
    }
}
