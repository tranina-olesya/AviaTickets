package ru.vsu.aviatickets;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.Carrier;
import ru.vsu.aviatickets.ticketssearch.models.Flight;
import ru.vsu.aviatickets.ticketssearch.models.Place;
import ru.vsu.aviatickets.ticketssearch.models.PriceLink;
import ru.vsu.aviatickets.ticketssearch.models.Ticket;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.sort.SortFilterType;
import ru.vsu.aviatickets.ticketssearch.sort.SortTrips;
import ru.vsu.aviatickets.ticketssearch.utils.TripUtils;
import ru.vsu.aviatickets.ui.utils.DateConvert;

import static org.junit.Assert.assertEquals;

public class TripsUnitTest {
    private List<Trip> trips = new ArrayList<>();

    @Before
    public void fillListTrips() {
        List<Ticket> flightParts;
        Flight flight;
        Place origin = new Place("MOW", "airport", "Москва");
        Place destination = new Place("LON", "airport", "Лондон");
        Date dateOutbound = DateConvert.getDateFromStringWithSlashes("20/10/2020");
        Date dateInbound = DateConvert.getDateFromStringWithSlashes("26/10/2020");

        flightParts = new ArrayList<>();
        flightParts.add(new Ticket(new Carrier(), origin, new Place(), new Date(), new Date(), 60, null));
        flightParts.add(new Ticket(new Carrier(), new Place(), destination, new Date(), new Date(), 80, null));
        flight = new Flight(origin, destination, dateOutbound, dateInbound, 340, 1, 0, 0, flightParts);
        Trip trip1 = new Trip(flight, null, null, 10000.0);
        trips.add(trip1);

        flightParts = new ArrayList<>();
        flightParts.add(new Ticket(new Carrier(), origin, destination, new Date(), new Date(), 60, null));
        flight = new Flight(origin, destination, dateOutbound, dateInbound, 60, 1, 0, 0, flightParts);
        Trip trip2 = new Trip(flight, null, null, 5000.0);
        trips.add(trip2);

        flightParts = new ArrayList<>();
        flightParts.add(new Ticket(new Carrier(), origin, new Place(), new Date(), new Date(), 60, null));
        flightParts.add(new Ticket(new Carrier(), new Place(), new Place(), new Date(), new Date(), 80, null));
        flightParts.add(new Ticket(new Carrier(), new Place(), destination, new Date(), new Date(), 80, null));
        flight = new Flight(origin, destination, dateOutbound, dateInbound, 250, 1, 0, 0, flightParts);
        Trip trip3 = new Trip(flight, null, null, 2000.0);
        trips.add(trip3);
    }

    @Test
    public void sort_shouldSortByPriceAsc() {
        List<Trip> result = new ArrayList<>();
        result.add(trips.get(2));
        result.add(trips.get(1));
        result.add(trips.get(0));
        SortTrips.sortTrips(trips, SortFilterType.MIN_PRICE);
        assertEquals(trips, result);
    }

    @Test
    public void sort_shouldSortByPriceDesc() {
        List<Trip> result = new ArrayList<>();
        result.add(trips.get(0));
        result.add(trips.get(1));
        result.add(trips.get(2));
        SortTrips.sortTrips(trips, SortFilterType.MAX_PRICE);
        assertEquals(trips, result);
    }

    @Test
    public void sort_shouldSortByTransfersAsc() {
        List<Trip> result = new ArrayList<>();
        result.add(trips.get(1));
        result.add(trips.get(0));
        result.add(trips.get(2));
        SortTrips.sortTrips(trips, SortFilterType.MIN_TRANSFERS);
        assertEquals(trips, result);
    }


    @Test
    public void sort_shouldSortByTransfersDesc() {
        List<Trip> result = new ArrayList<>();
        result.add(trips.get(2));
        result.add(trips.get(0));
        result.add(trips.get(1));
        SortTrips.sortTrips(trips, SortFilterType.MAX_TRANSFERS);
        assertEquals(trips, result);
    }

    @Test
    public void sort_shouldSortByTimeAsc() {
        List<Trip> result = new ArrayList<>();
        result.add(trips.get(1));
        result.add(trips.get(2));
        result.add(trips.get(0));
        SortTrips.sortTrips(trips, SortFilterType.MIN_TIME);
        assertEquals(trips, result);
    }

    @Test
    public void sort_shouldSortByTimeDesc() {
        List<Trip> result = new ArrayList<>();
        result.add(trips.get(0));
        result.add(trips.get(2));
        result.add(trips.get(1));
        SortTrips.sortTrips(trips, SortFilterType.MAX_TIME);
        assertEquals(trips, result);
    }

    @Test
    public void price_shouldFindMinPriceLink() {
        List<PriceLink> priceLinks = new ArrayList<>();
        priceLinks.add(new PriceLink(null, 900.0, null));
        priceLinks.add(new PriceLink(null, 1200.0, null));
        priceLinks.add(new PriceLink(null, 712.2, null));
        priceLinks.add(new PriceLink(null, 871.0, null));
        PriceLink minPriceLink = TripUtils.getMinPriceLink(priceLinks);
        assertEquals(minPriceLink, priceLinks.get(2));
    }
}