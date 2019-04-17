package ru.vsu.aviatickets.ui.ticketresults;

import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.Trip;

public interface TripContractView {
    void showTrips(List<Trip> trips);

    void showProgress();

    void hideProgress();
}
