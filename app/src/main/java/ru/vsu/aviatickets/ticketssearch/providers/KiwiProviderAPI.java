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

public class KiwiProviderAPI extends ProviderAPI<KiwiAPI> {
    public KiwiProviderAPI() {
        super("https://api.skypicker.com");
    }

    @Override
    public void getTickets(TicketsCallback callback) {
        getTicketsApi().getTickets("VOZ", "MOW", "18/04/2019", "18/04/2019", "20/04/2019", "20/04/2019","oneway").enqueue(new Callback<KiwiResponse>() {
            @Override
            public void onResponse(Call<KiwiResponse> call, Response<KiwiResponse> response) {
                if (response.body() != null)
                    convertResponseToTrip(response.body());
            }

            @Override
            public void onFailure(Call<KiwiResponse> call, Throwable t) {

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

    private List<Trip> convertResponseToTrip(KiwiResponse response){
        List<Trip> trips = new ArrayList<>();
        for (Datum data: response.getData()){
            Trip trip = new Trip();

            List<PriceLink> priceLinks = new ArrayList<>();
            List<Agent> agents = new ArrayList<>();
            agents.add(new Agent("Kiwi.com", null));
            PriceLink priceLink = new PriceLink(agents, data.getPrice(), data.getDeepLink());
            priceLinks.add(priceLink);
            trip.setPriceLinks(priceLinks);

            Flight outbound = new Flight();
            outbound.setAdultsCount(response.getSearchParams().getSeats().getAdults());
            outbound.setChildrenCount(response.getSearchParams().getSeats().getChildren());
            outbound.setInfantsCount(response.getSearchParams().getSeats().getInfants());
            outbound.setDuration(data.getDuration().getDeparture());
            outbound.setOutboundDate(new Date(data.getATime()*1000L));
            outbound.setInboundDate(new Date(data.getDTime()*1000L));
            outbound.setOrigin(new Place(data.getFlyFrom(), null, data.getCityFrom()));
            outbound.setDestination(new Place(data.getFlyTo(), null, data.getCityTo()));
            outbound.setFlightParts(formTicketsList(data, 0));

        }
        return trips;
    }

    private List<Route> getRoutesByDirection(List<Route> routes, int direction){
        return routes.stream().filter(r->r.getReturn()==direction).collect(Collectors.toList());
    }

    private List<Ticket> formTicketsList(Datum data, int direction){
        List<Ticket> tickets = new ArrayList<>();
        for (Route route: getRoutesByDirection(data.getRoute(), direction)){
            Ticket ticket = new Ticket();
            Carrier carrier = new Carrier(route.getOperatingCarrier(), route.getAirline(), null);
            ticket.setCarrier(carrier);
            ticket.setOrigin(new Place(route.getFlyFrom(), null, route.getCityFrom()));
            ticket.setDestination(new Place(route.getFlyTo(), null, route.getCityTo()));
            ticket.setOutboundDate(new Date(route.getDTime() *1000L));
            ticket.setInboundDate(new Date(route.getATime() *1000L));
            ticket.setDuration((int)((route.getDTime() - route.getATime())/(60000L)));
            tickets.add(ticket);
        }
        return tickets;
    }
}
