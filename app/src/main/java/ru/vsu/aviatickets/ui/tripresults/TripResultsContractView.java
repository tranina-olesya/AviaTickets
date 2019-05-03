package ru.vsu.aviatickets.ui.tripresults;

import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.Trip;

public interface TripResultsContractView {
    void showTrips(List<Trip> trips);

    void showProgress();

    void hideProgress();

    void cityNotFound();

    void ticketsNotFound();

    void noResponse();
}
