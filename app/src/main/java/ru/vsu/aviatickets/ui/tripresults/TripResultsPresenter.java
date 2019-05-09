package ru.vsu.aviatickets.ui.tripresults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.providers.APIError;
import ru.vsu.aviatickets.ticketssearch.sort.SortFilterType;

public class TripResultsPresenter {
    private TripResultsContractView view;
    private final TripResultsModel model;
    private final BookmarkAdditionModel modelAddition;

    private List<Trip> lastFoundTrips;

    public TripResultsPresenter(TripResultsModel model, BookmarkAdditionModel modelAddition ) {
        this.model = model;
        this.modelAddition = modelAddition;
        lastFoundTrips = new ArrayList<>();
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
        view.hideGroupTripResults();
        view.showProgress();
        model.loadTrips(searchData, new TripResultsModel.ResultsCallback() {
            @Override
            public void onGet(List<Trip> trips) {
                view.hideProgress();
                view.showGroupTripResults();
                if (trips == null || trips.isEmpty())
                    view.ticketsNotFound();
                else {
                    lastFoundTrips = trips;
                    view.showTrips(lastFoundTrips);
                }
            }

            @Override
            public void onFail(List<APIError> errors) {
                view.hideProgress();
                view.showGroupTripResults();
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

    public void filterChosen(SortFilterType sortFilterType) {
        model.sortTripsByFilter(lastFoundTrips, sortFilterType, new TripResultsModel.SortCallback() {
            @Override
            public void onComplete(List<Trip> trips) {
                view.showTrips(trips);
            }
        });
    }

    public void addBookmark() {
        BookmarkRoute bookmarkRoute = view.addBookmarkRouteData();

        modelAddition.addBookmarkRoute(bookmarkRoute, new BookmarkAdditionModel.CompleteCallback() {
            @Override
            public void onComplete() {
                // вывести сообщение
            }
        });
    }
}
