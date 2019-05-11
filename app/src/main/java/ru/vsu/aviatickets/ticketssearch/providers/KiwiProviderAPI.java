package ru.vsu.aviatickets.ticketssearch.providers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.vsu.aviatickets.ticketssearch.api.interfaces.KiwiAPI;
import ru.vsu.aviatickets.ticketssearch.models.Agent;
import ru.vsu.aviatickets.ticketssearch.models.CabinClass;
import ru.vsu.aviatickets.ticketssearch.models.Carrier;
import ru.vsu.aviatickets.ticketssearch.models.Flight;
import ru.vsu.aviatickets.ticketssearch.models.FlightType;
import ru.vsu.aviatickets.ticketssearch.models.Place;
import ru.vsu.aviatickets.ticketssearch.models.PriceLink;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Ticket;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.Datum;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.KiwiResponse;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.Route;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.SearchParams;
import ru.vsu.aviatickets.ticketssearch.utils.TripUtils;

public class KiwiProviderAPI extends ProviderAPI<KiwiAPI> implements TicketProviderApi {
    private IATAProviderAPI iataProviderAPI;

    public KiwiProviderAPI() {
        super("https://api.skypicker.com");
        iataProviderAPI = new IATAProviderAPI();
    }

    @Override
    public void getTickets(SearchData searchData, TicketsCallback callback) {
        iataProviderAPI.getCityCodes(searchData.getOrigin(), searchData.getDestination(), new CityCallback() {
            @Override
            public void onGet(String originCode, String destinationCode) {
                if (searchData.getCabinClass() == CabinClass.BUSINESS) {
                    callback.onFail(APIError.TICKETS_NOT_FOUND);
                    return;
                }
                String outboundDate = convertDateToString(searchData.getOutboundDate());
                String inboundDate = searchData.getFlightType() == FlightType.ROUND ?
                        convertDateToString(searchData.getInboundDate()) : null;
                getApi().getTickets(originCode, destinationCode, outboundDate, outboundDate, inboundDate, inboundDate,
                        searchData.getFlightType().toString().toLowerCase(), searchData.getAdultsCount(), searchData.getChildrenCount(),
                        searchData.getInfantsCount(), searchData.getTransfers() ? 0 : 1).enqueue(new Callback<KiwiResponse>() {
                    @Override
                    public void onResponse(Call<KiwiResponse> call, Response<KiwiResponse> response) {
                        if (response.body() != null) {
                            callback.onGet(convertResponseToTrip(response.body()));
                        } else {
                            callback.onFail(APIError.TICKETS_NOT_FOUND);
                        }
                    }

                    @Override
                    public void onFailure(Call<KiwiResponse> call, Throwable t) {
                        callback.onFail(APIError.NO_RESPONSE);
                    }
                });
            }

            @Override
            public void onFail(APIError error) {
                callback.onFail(error);
            }
        });
    }

    @Override
    protected KiwiAPI createApiClass(Retrofit retrofit) {
        return retrofit.create(KiwiAPI.class);
    }

    private String convertDateToString(Date date) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    private List<Trip> convertResponseToTrip(KiwiResponse response) {
        List<Trip> trips = new ArrayList<>();
        for (Datum data : response.getData()) {
            Trip trip = new Trip();

            List<PriceLink> priceLinks = new ArrayList<>();
            List<Agent> agents = new ArrayList<>();
            agents.add(new Agent("Kiwi.com", "https://s1.apideeplink.com/images/websites/skyp.png"));
            PriceLink priceLink = new PriceLink(agents, data.getPrice(), data.getDeepLink());
            priceLinks.add(priceLink);
            trip.setPriceLinks(priceLinks);
            trip.setOutbound(formFlight(response.getSearchParams(), data, 0));
            trip.setInbound(formFlight(response.getSearchParams(), data, 1));
            trip.setMinPrice(TripUtils.getMinPriceLink(trip.getPriceLinks()).getPrice());

            trips.add(trip);
        }
        return trips;
    }

    private List<Route> getRoutesByDirection(List<Route> routes, int direction) {
        List<Route> results = new ArrayList<>();
        for (Route route : routes) {
            if (route.getReturn() == direction)
                results.add(route);
        }
        return results;
    }

    private List<Ticket> formTicketsList(SearchParams searchParams, Datum data, int direction) {
        List<Ticket> tickets = new ArrayList<>();
        for (Route route : getRoutesByDirection(data.getRoute(), direction)) {
            Ticket ticket = new Ticket();
            String operatingCarrier = !route.getOperatingCarrier().isEmpty() ? route.getOperatingCarrier() : route.getAirline();
            ticket.setCarrier(new Carrier(operatingCarrier, route.getAirline(), String.format("https://pics.avs.io/120/60/%s.png", route.getAirline())));
            ticket.setOrigin(new Place(route.getFlyFrom(), searchParams.getFlyFromType(), route.getCityFrom()));
            ticket.setDestination(new Place(route.getFlyTo(), searchParams.getToType(), route.getCityTo()));
            ticket.setOutboundDate(new Date(route.getDTime() * 1000L));
            ticket.setInboundDate(new Date(route.getATime() * 1000L));
            ticket.setDuration((int) ((route.getATime() - route.getDTime()) / (60L)));
            ticket.setFlightNumber(route.getFlightNo().toString());
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
            flight.setDuration((data.getDuration().getDeparture() / 60));
        else
            flight.setDuration((data.getDuration().getReturn() / 60));
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
