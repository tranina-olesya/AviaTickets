package ru.vsu.aviatickets.ui.ticketresults;

import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.providers.TicketProviderApi;

public class TripPresenter {
    private TripContractView view;
    private final TripModel model;

    public TripPresenter(TripModel model) {
        this.model = model;
    }

    public void attachView(TripContractView activity) {
        view = activity;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady(SearchData searchData) {
        loadData(searchData);
    }

    public void loadData(SearchData searchData) {
        model.loadTrips(searchData, new TicketProviderApi.TicketsCallback() {
            @Override
            public void onGet(List<Trip> trips) {
                view.showTrips(trips);
            }

            @Override
            public void onFail() {

            }
        });
    }


}