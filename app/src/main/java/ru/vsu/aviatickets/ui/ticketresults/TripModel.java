package ru.vsu.aviatickets.ui.ticketresults;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.providers.TicketProviderApi;

public class TripModel {
    private List<TicketProviderApi> providers;

    public TripModel(List<TicketProviderApi> providers) {
        this.providers = providers;
    }

    private int count = 0;

    public void loadTrips(SearchData searchData, TicketProviderApi.TicketsCallback callback) {
        count = 0;

        List<List<Trip>> result = new ArrayList<>();
        for (TicketProviderApi providerAPI : providers) {
            providerAPI.getTickets(searchData, new TicketProviderApi.TicketsCallback() {
                @Override
                public void onGet(List<Trip> trips) {
                    count++;
                    result.add(trips);
                    if (count == providers.size())
                        callback.onGet(mergeTrips(result));
                }

                @Override
                public void onFail() {
                    count++;
                    callback.onFail();
                    if (count == providers.size())
                        callback.onGet(mergeTrips(result));
                }
            });
        }
    }

    private List<Trip> mergeTrips(List<List<Trip>> trips) {
        List<Trip> result = new ArrayList<>();
        for (List<Trip> list : trips) {
            if (result.isEmpty()) {
                result.addAll(list);
            } else {
                for (Trip trip : list) {
                    int index = findTripIndex(result, trip);
                    if (index >= 0) {
                        Trip resTrip = result.get(index);
                        resTrip.getPriceLinks().addAll(trip.getPriceLinks());
                    } else
                        result.add(trip);
                }
            }
        }
        return result;
    }

    private int findTripIndex(List<Trip> trips, Trip p) {
        for (int i = 0; i < trips.size(); i++) {
            if (p.equals(trips.get(i)))
                return i;
        }
        return -1;
    }


    private int findSimilarTripIndex(List<Trip> trips, Trip p) {
        for (int i = 0; i < trips.size(); i++) {
            if (p.getInbound().getInboundDate().equals(trips.get(i).getInbound().getInboundDate()) &&
                    p.getInbound().getOutboundDate().equals(trips.get(i).getInbound().getOutboundDate()) &&
                    p.getOutbound().getInboundDate().equals(trips.get(i).getInbound().getInboundDate()) &&
                    p.getOutbound().getOutboundDate().equals(trips.get(i).getInbound().getOutboundDate())
            )
                return i;
        }
        return -1;
    }
}
