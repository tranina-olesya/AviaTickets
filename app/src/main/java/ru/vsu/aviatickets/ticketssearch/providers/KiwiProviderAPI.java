package ru.vsu.aviatickets.ticketssearch.providers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.vsu.aviatickets.ticketssearch.api.interfaces.KiwiAPI;
import ru.vsu.aviatickets.ticketssearch.models.Agent;
import ru.vsu.aviatickets.ticketssearch.models.Carrier;
import ru.vsu.aviatickets.ticketssearch.models.Flight;
import ru.vsu.aviatickets.ticketssearch.models.Place;
import ru.vsu.aviatickets.ticketssearch.models.PriceLink;
import ru.vsu.aviatickets.ticketssearch.models.Ticket;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.Datum;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.KiwiResponse;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.Route;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.SearchParams;

public class KiwiProviderAPI extends ProviderAPI<KiwiAPI> {
    public KiwiProviderAPI() {
        super("https://api.skypicker.com");
    }

    @Override
    public void getTickets(TicketsCallback callback) {
        getTicketsApi().getTickets("VOZ", "MOW", "18/04/2019", "18/04/2019", "20/04/2019", "20/04/2019", "round").enqueue(new Callback<KiwiResponse>() {
            @Override
            public void onResponse(Call<KiwiResponse> call, Response<KiwiResponse> response) {
                if (response.body() != null) {
                    callback.onGet(convertResponseToTrip(response.body()));
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<KiwiResponse> call, Throwable t) {
                callback.onFail();
            }
        });
    }

    @Override
    public List<Trip> sortTickets() {
        return null;
    }

    @Override
    protected KiwiAPI createApiClass(Retrofit retrofit) {
        return retrofit.create(KiwiAPI.class);
    }

    private List<Trip> convertResponseToTrip(KiwiResponse response) {
        List<Trip> trips = new ArrayList<>();
        for (Datum data : response.getData()) {
            Trip trip = new Trip();

            List<PriceLink> priceLinks = new ArrayList<>();
            List<Agent> agents = new ArrayList<>();
            agents.add(new Agent("Kiwi.com", null));
            PriceLink priceLink = new PriceLink(agents, data.getPrice(), data.getDeepLink());
            priceLinks.add(priceLink);
            trip.setPriceLinks(priceLinks);
            trip.setOutbound(formFlight(response.getSearchParams(), data, 0));
            trip.setInbound(formFlight(response.getSearchParams(), data, 1));

            trips.add(trip);
        }
        return trips;
    }

    private List<Route> getRoutesByDirection(List<Route> routes, int direction) {
        return routes.stream().filter(r -> r.getReturn() == direction).collect(Collectors.toList());
    }

    private List<Ticket> formTicketsList(SearchParams searchParams, Datum data, int direction) {
        List<Ticket> tickets = new ArrayList<>();
        for (Route route : getRoutesByDirection(data.getRoute(), direction)) {
            Ticket ticket = new Ticket();
            ticket.setCarrier(new Carrier(route.getOperatingCarrier(), route.getAirline(), null));
            ticket.setOrigin(new Place(route.getFlyFrom(), searchParams.getFlyFromType(), route.getCityFrom()));
            ticket.setDestination(new Place(route.getFlyTo(), searchParams.getToType(), route.getCityTo()));
            ticket.setOutboundDate(new Date(route.getDTime() * 1000L));
            ticket.setInboundDate(new Date(route.getATime() * 1000L));
            ticket.setDuration((int) ((route.getDTime() - route.getATime()) / (60000L)));
            tickets.add(ticket);
        }
        return tickets;
    }

    private Flight formFlight(SearchParams searchParams, Datum data, int direction) {
        Flight flight = new Flight();
        flight.setAdultsCount(searchParams.getSeats().getAdults());
        flight.setChildrenCount(searchParams.getSeats().getChildren());
        flight.setInfantsCount(searchParams.getSeats().getInfants());

        if (direction == 0)
            flight.setDuration(data.getDuration().getDeparture());
        else
            flight.setDuration(data.getDuration().getReturn());
        List<Ticket> flightParts = formTicketsList(searchParams, data, direction);
        if (flightParts.isEmpty())
            return null;

        flight.setFlightParts(flightParts);
        Ticket start = flightParts.get(0);
        Ticket end = flightParts.get(flightParts.size() - 1);
        flight.setOutboundDate(start.getOutboundDate());
        flight.setInboundDate(end.getInboundDate());
        flight.setOrigin(start.getOrigin());
        flight.setDestination(end.getDestination());
        return flight;
    }
}
