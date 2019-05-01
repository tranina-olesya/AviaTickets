package ru.vsu.aviatickets.ui.tripresults;

import java.util.List;
import java.util.stream.Collectors;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.providers.APIError;

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
        model.loadTrips(searchData, new TripResultsModel.ResultsCallback() {
            @Override
            public void onGet(List<Trip> trips) {
                view.hideProgress();
                if (trips == null || trips.isEmpty())
                    view.ticketsNotFound();
                else
                    view.showTrips(trips);
            }

            @Override
            public void onFail(List<APIError> errors) {
                view.hideProgress();
                if (errors.size() == model.getProvidersCount()) {
                    APIError apiError = checkForErrorType(errors);
                    if (apiError != null) {
                        if (apiError == APIError.CITY_NOT_FOUND)
                            view.cityNotFound();
                         else if (apiError == APIError.NO_RESPONSE)
                            view.noResponse();
                    }
                }
            }
        });
    }

    private APIError checkForErrorType(List<APIError> errors) {
        List<APIError> apiErrors = errors.stream().distinct().collect(Collectors.toList());
        if (apiErrors.size() == 1) {
            return apiErrors.get(0);
        } else
            return null;
    }
}
