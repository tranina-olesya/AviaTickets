package ru.vsu.aviatickets.ui.tripresults;

import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.providers.TicketProviderApi;

public class TripResultsPresenter {
    private TripResultsContractView view;
    private final TripResultsModel model;

    public TripResultsPresenter(TripResultsModel model) {
        this.model = model;
    }

    public void attachView(TripResultsContractView view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady(SearchData searchData) {
        if (searchData != null) {
            loadData(searchData);
        }
    }

    public void loadData(SearchData searchData) {
        view.showProgress();
        model.loadTrips(searchData, new TicketProviderApi.TicketsCallback() {
            @Override
            public void onGet(List<Trip> trips) {
                view.showTrips(trips);
                view.hideProgress();
            }

            @Override
            public void onFail() {
                view.hideProgress();
            }
        });
    }


}
