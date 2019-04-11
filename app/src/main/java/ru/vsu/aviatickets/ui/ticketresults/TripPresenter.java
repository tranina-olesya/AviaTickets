package ru.vsu.aviatickets.ui.ticketresults;

import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.providers.ProviderAPI;

public class TripPresenter {
    private TripContractView view;
    private final TripModel model;

    public TripPresenter(TripModel model) {
        this.model = model;
    }

    public void attachView(TripContractView activity){
        view = activity;
    }

    public void detachView(){
        view = null;
    }

    public void viewIsReady(){
        loadData();
    }

    public void loadData(){
        model.loadTrips(new ProviderAPI.TicketsCallback() {
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
