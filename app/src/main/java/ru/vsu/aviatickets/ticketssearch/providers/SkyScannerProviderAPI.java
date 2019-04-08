package ru.vsu.aviatickets.ticketssearch.providers;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.vsu.aviatickets.ticketssearch.api.interfaces.SkyScannerAPI;
import ru.vsu.aviatickets.ticketssearch.models.Agent;
import ru.vsu.aviatickets.ticketssearch.models.Carrier;
import ru.vsu.aviatickets.ticketssearch.models.Flight;
import ru.vsu.aviatickets.ticketssearch.models.PriceLink;
import ru.vsu.aviatickets.ticketssearch.models.Ticket;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.Itinerary;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.Leg;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerPlace;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.PricingOption;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.Segment;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerAgent;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerCarrier;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerResponse;

public class SkyScannerProviderAPI extends ProviderAPI<SkyScannerAPI> {
    public SkyScannerProviderAPI() {
        super("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com");
    }

    @Override
    protected SkyScannerAPI createApiClass(Retrofit retrofit) {
        return retrofit.create(SkyScannerAPI.class);
    }

    @Override
    public List<Trip> sortTickets() {
        return null;
    }

    @Override
    public void getTickets(final TicketsCallback ticketsCallback) {
        getSessionKey(new SessionKeyCallback() {
            @Override
            public void onGet(String sessionKey) {
                getTicketsApi().pollSessionResults(sessionKey, 0, 10, null, null, null).enqueue(new Callback<SkyScannerResponse>() {
                    @Override
                    public void onResponse(Call<SkyScannerResponse> call, Response<SkyScannerResponse> response) {
                        SkyScannerResponse body = response.body();
                        if (body != null) {
                            List<Trip> trips = convertResponseToTrip(body);
                            ticketsCallback.onGet(trips);
                        } else
                            ticketsCallback.onFail();
                    }

                    @Override
                    public void onFailure(Call<SkyScannerResponse> call, Throwable t) {
                        ticketsCallback.onFail();
                    }
                });
            }

            @Override
            public void onFail() {
                ticketsCallback.onFail();
            }
        });
    }

    private void getSessionKey(final SessionKeyCallback callback) {
        getTicketsApi().createSession("2019-04-18", null, "business", 0, 0, "RU", "RUB", "ru-RU", "VOZ-sky", "MOSC-sky", 1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (callback != null) {
                    String key = response.headers().get("location");
                    if (key != null) {
                        callback.onGet(key.substring(key.lastIndexOf("/")));
                    } else
                        callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback != null)
                    callback.onFail();
            }
        });
    }

    private List<Trip> convertResponseToTrip(SkyScannerResponse response) {
        List<Trip> trips = new ArrayList<>();
        for (final Itinerary itinerary : response.getItineraries()) {
            Trip trip = new Trip();
            Leg outboundLeg = findLegById(response.getLegs(), itinerary.getOutboundLegId());
            Leg inboundLeg = findLegById(response.getLegs(), itinerary.getInboundLegId());
            Flight outbound = formFlight(response, outboundLeg);
            Flight inbound = formFlight(response, inboundLeg);
            trip.setOutbound(outbound);
            trip.setInbound(inbound);
            List<PriceLink> priceLinks = formPriceLinks(response.getSkyScannerAgents(), itinerary);
            trip.setPriceLinks(priceLinks);
            trips.add(trip);
        }
        return trips;
    }

    private SkyScannerPlace findPlaceById(List<SkyScannerPlace> places, Integer id) {
        if (id == null)
            return null;
        for (SkyScannerPlace place : places) {
            if (place.getId().equals(id))
                return place;
        }
        return null;
    }

    private Leg findLegById(List<Leg> legs, String id) {
        if (id == null)
            return null;
        for (Leg leg : legs) {
            if (leg.getId().equals(id))
                return leg;
        }
        return null;
    }

    private Carrier findCarrierById(List<SkyScannerCarrier> carriers, Integer id) {
        if (id == null)
            return null;
        for (SkyScannerCarrier carrier : carriers) {
            if (carrier.getId().equals(id))
                return carrier;
        }
        return null;
    }

    private SkyScannerAgent findAgentById(List<SkyScannerAgent> agents, Integer id) {
        if (id == null)
            return null;
        for (SkyScannerAgent agent : agents) {
            if (agent.getId().equals(id))
                return agent;
        }
        return null;
    }

    private Segment findSegmentById(List<Segment> segments, Integer id) {
        if (id == null)
            return null;
        for (Segment segment : segments) {
            if (segment.getId().equals(id))
                return segment;
        }
        return null;
    }

    private Flight formFlight(final SkyScannerResponse response, final Leg leg) {
        if (response == null || leg == null)
            return null;
        Flight flight = new Flight();
        flight.setAdultsCount(response.getQuery().getAdults());
        flight.setChildrenCount(response.getQuery().getChildren());
        flight.setInfantsCount(response.getQuery().getInfants());
        flight.setOutboundDate(leg.getDeparture());
        flight.setInboundDate(leg.getArrival());
        flight.setDuration(leg.getDuration());
        flight.setOrigin(findPlaceById(response.getPlaces(), leg.getOriginStation()));
        flight.setDestination(findPlaceById(response.getPlaces(), leg.getDestinationStation()));
        for (Integer segmentID : leg.getSegmentIds()) {
            Ticket ticket = new Ticket();
            Segment segment = findSegmentById(response.getSegments(), segmentID);
            ticket.setOrigin(findPlaceById(response.getPlaces(), segment.getOriginStation()));
            ticket.setDestination(findPlaceById(response.getPlaces(), segment.getDestinationStation()));
            ticket.setOutboundDate(segment.getDepartureDateTime());
            ticket.setInboundDate(segment.getArrivalDateTime());
            ticket.setDuration(segment.getDuration());
            ticket.setCarrier(findCarrierById(response.getCarriers(), segment.getCarrier()));
            flight.getFlightParts().add(ticket);

        }
        return flight;
    }

    private List<PriceLink> formPriceLinks(List<SkyScannerAgent> skyScannerAgents, Itinerary itinerary){
        List<PriceLink> priceLinks = new ArrayList<>();
        for (PricingOption pricingOption : itinerary.getPricingOptions()) {
            PriceLink priceLink = new PriceLink();
            List<Agent> agents = new ArrayList<>();
            for (Integer agentId : pricingOption.getAgents()) {
                SkyScannerAgent agent = findAgentById(skyScannerAgents, agentId);
                agents.add(new Agent(agent.getName(), agent.getImageUrl()));
            }
            priceLink.setAgents(agents);
            priceLink.setDeepLink(pricingOption.getDeeplinkUrl());
            priceLink.setPrice(pricingOption.getPrice());
            priceLinks.add(priceLink);
        }
        return priceLinks;
    }

}


