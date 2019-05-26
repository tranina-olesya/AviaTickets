package ru.vsu.aviatickets.ui.tripresults;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.entities.tripmodels.Trip;
import ru.vsu.aviatickets.api.providers.APIError;
import ru.vsu.aviatickets.api.providers.BookmarkAPIProvider;
import ru.vsu.aviatickets.api.providers.TripAPIProvider;
import ru.vsu.aviatickets.ticketssearch.sort.SortFilterType;
import ru.vsu.aviatickets.ui.bookmarks.BookmarksRouteModel;

public class TripResultsPresenter {
    private TripResultsContractView view;
    private final TripResultsModel model;
    private final BookmarkAdditionModel modelAddition;
    private BookmarkRoute savedBookmark;

    private List<Trip> lastFoundTrips;

    public TripResultsPresenter(TripResultsModel model, BookmarkAdditionModel modelAddition) {
        this.model = model;
        this.modelAddition = modelAddition;
        lastFoundTrips = new ArrayList<>();
    }

    public void attachView(TripResultsContractView view) {
        this.view = view;
    }

    public void viewIsReady(SearchData searchData) {
        checkIfBookmarkExists(searchData);
        if (searchData != null) {
            loadData(searchData);
        }
    }

    private void checkIfBookmarkExists(SearchData searchData) {
        modelAddition.findBookmark(searchData, new BookmarkAPIProvider.BookmarkCallback() {
            @Override
            public void onComplete(BookmarkRoute bookmarkRoute) {
                savedBookmark = bookmarkRoute;
                if (savedBookmark != null)
                    view.bookmarkAdded();
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void loadData(SearchData searchData) {
        view.hideGroupTripResults();
        view.showProgress();
        model.loadTrips(searchData, new TripAPIProvider.TripsCallback() {
            @Override
            public void onComplete(List<Trip> trips) {
                view.hideProgress();
                view.showGroupTripResults();
                if (trips == null || trips.isEmpty())
                    view.ticketsNotFound();
                else {
                    lastFoundTrips = trips;
                    updateViewTrips(trips);
                }
            }

            @Override
            public void onFail(/*List<APIError> errors*/) {
                view.hideProgress();
                view.showGroupTripResults();
//                if (errors.size() == model.getProvidersCount()) {
//                    APIError apiError = checkForErrorType(errors);
//                    if (apiError != null) {
//                        if (apiError == APIError.NO_RESPONSE)
//                            view.noResponse();
//                    }
//                }
            }
        });
    }

    private void updateViewTrips(List<Trip> trips) {
        view.setLoadedTrips(trips);
        view.showTrips();
        view.loadMore();
    }

    private APIError checkForErrorType(List<APIError> errors) {
        List<APIError> apiErrors = removeDuplicates(errors);
        if (apiErrors.size() == 1) {
            return apiErrors.get(0);
        } else
            return null;
    }

    public void filterChosen(SortFilterType sortFilterType) {
        model.sortTripsByFilter(lastFoundTrips, sortFilterType, new TripResultsModel.SortCallback() {
            @Override
            public void onComplete(List<Trip> trips) {
                updateViewTrips(trips);
            }
        });
    }

    public void bookmarkButtonClicked() {
        view.disableBookmarksButton();
        if (savedBookmark == null) {
            BookmarkRoute bookmarkRoute = view.addBookmarkRouteData();
            modelAddition.addBookmarkRoute(bookmarkRoute, new CompleteCallback() {
                @Override
                public void onComplete() {
                    checkIfBookmarkExists(view.getSearchData());
                    view.bookmarkAdded();
                    view.enableBookmarksButton();
                }

                @Override
                public void onFail() {

                }
            });
        } else {
            modelAddition.deleteBookmarkRoute(savedBookmark, new CompleteCallback() {
                @Override
                public void onComplete() {
                    savedBookmark = null;
                    view.bookmarkDeleted();
                    view.enableBookmarksButton();
                }

                @Override
                public void onFail() {

                }
            });
        }
    }

    private List<APIError> removeDuplicates(List<APIError> apiErrors) {
        LinkedHashSet<APIError> hashSet = new LinkedHashSet<APIError>(apiErrors);
        return new ArrayList<>(hashSet);
    }
}
