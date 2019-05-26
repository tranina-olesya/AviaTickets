package ru.vsu.aviatickets.ui.tripresults;

import android.os.AsyncTask;

import java.util.List;

import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.entities.tripmodels.Trip;
import ru.vsu.aviatickets.api.providers.TripAPIProvider;
import ru.vsu.aviatickets.ticketssearch.sort.SortFilterType;
import ru.vsu.aviatickets.ticketssearch.sort.SortTrips;

public class TripResultsModel {

    private TripAPIProvider tripAPIProvider;

    public TripResultsModel() {
        this.tripAPIProvider = new TripAPIProvider();
    }

    public void loadTrips(SearchData searchData, TripAPIProvider.TripsCallback callback) {
        tripAPIProvider.getTrips(searchData, callback);
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
