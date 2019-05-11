package ru.vsu.aviatickets.ui.tripresults;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import ru.vsu.aviatickets.ticketssearch.models.PriceLink;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.providers.APIError;
import ru.vsu.aviatickets.ticketssearch.providers.TicketProviderApi;
import ru.vsu.aviatickets.ticketssearch.sort.SortFilterType;
import ru.vsu.aviatickets.ticketssearch.sort.SortTrips;
import ru.vsu.aviatickets.ticketssearch.utils.TripUtils;

public class TripResultsModel {
    public interface ResultsCallback {
        void onGet(List<Trip> trips);

        void onFail(List<APIError> errors);
    }

    private List<TicketProviderApi> providers;

    public TripResultsModel(List<TicketProviderApi> providers) {
        this.providers = providers;
    }

    private int count = 0;

    public void loadTrips(SearchData searchData, ResultsCallback callback) {
        count = 0;
        List<List<Trip>> result = new ArrayList<>();
        List<APIError> errors = new ArrayList<>();

        for (TicketProviderApi providerAPI : providers) {
            providerAPI.getTickets(searchData, new TicketProviderApi.TicketsCallback() {
                @Override
                public void onGet(List<Trip> trips) {
                    count++;
                    result.add(trips);
                    if (count == providers.size()) {
                        returnResults();
                    }
                }

                @Override
                public void onFail(APIError error) {
                    count++;
                    errors.add(error);
                    if (count == providers.size()) {
                        returnResults();
                    }
                }

                public void returnResults() {
                    if (errors.size() == providers.size())
                        callback.onFail(errors);
                    else
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
                        resTrip.setMinPrice(TripUtils.getMinPriceLink(trip.getPriceLinks()).getPrice());
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

    public int getProvidersCount() {
        return providers.size();
    }


    public void sortTripsByFilter(List<Trip> trips, SortFilterType sortFilterType, SortCallback callback) {
        SortTripsAsyncTask sortTripsAsyncTask = new SortTripsAsyncTask(callback);
        sortTripsAsyncTask.execute(trips, sortFilterType);
    }

    public static class SortTripsAsyncTask extends AsyncTask<Object, Void, List<Trip>> {

        private SortCallback callback;

        SortTripsAsyncTask(SortCallback callback) {
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(List<Trip> trips) {
            super.onPostExecute(trips);
            if (callback != null)
                callback.onComplete(trips);
        }

        @Override
        protected List<Trip> doInBackground(Object... objects) {
            List<Trip> trips = (List<Trip>) objects[0];
            SortFilterType sortFilterType = (SortFilterType) objects[1];
            SortTrips.sortTrips(trips, sortFilterType);
            return trips;
        }
    }

    interface SortCallback {
        void onComplete(List<Trip> trips);
    }
}
