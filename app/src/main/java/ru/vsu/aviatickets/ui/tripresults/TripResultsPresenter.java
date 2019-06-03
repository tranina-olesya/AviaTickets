package ru.vsu.aviatickets.ui.tripresults;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.entities.tripmodels.Trip;
import ru.vsu.aviatickets.api.providers.APIError;
import ru.vsu.aviatickets.api.providers.BookmarkAPIProvider;
import ru.vsu.aviatickets.api.providers.TripAPIProvider;
import ru.vsu.aviatickets.ticketssearch.sort.SortFilterType;

public class TripResultsPresenter {
    private final TripResultsModel model;
    private final BookmarkAdditionModel modelAddition;
    private TripResultsContractView view;
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
        String userCode = App.getUserCode();
        if (userCode != null)
            modelAddition.findBookmark(userCode, searchData, new BookmarkAPIProvider.BookmarkCallback() {
                @Override
                public void onComplete(BookmarkRoute bookmarkRoute) {
                    savedBookmark = bookmarkRoute;
                    if (savedBookmark != null)
                        view.bookmarkAdded();
                }

                @Override
                public void onFail() {
//                view.showNoResponseToast();
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
            public void onFail(APIError error) {
                view.hideProgress();
                view.hideGroupTripResults();
                if (error != null) {
                    if (error == APIError.NO_RESPONSE)
                        view.noResponse();
                    else if (error == APIError.TICKETS_NOT_FOUND)
                        view.ticketsNotFound();
                }
            }
        });
    }

    private void updateViewTrips(List<Trip> trips) {
        view.setLoadedTrips(trips);
        view.showTrips();
        view.loadMore();
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
        String userCode = App.getUserCode();
        if (userCode != null) {
            view.disableBookmarksButton();
            if (savedBookmark == null) {
                BookmarkRoute bookmarkRoute = view.addBookmarkRouteData();
                modelAddition.addBookmarkRoute(userCode, bookmarkRoute, new CompleteCallback() {
                    @Override
                    public void onComplete() {
                        checkIfBookmarkExists(view.getSearchData());
                        view.bookmarkAdded();
                        view.enableBookmarksButton();
                    }

                    @Override
                    public void onFail() {
                        view.showNoResponseToast();
                    }
                });
            } else {
                modelAddition.deleteBookmarkRoute(userCode, savedBookmark, new CompleteCallback() {
                    @Override
                    public void onComplete() {
                        savedBookmark = null;
                        view.bookmarkDeleted();
                        view.enableBookmarksButton();
                    }

                    @Override
                    public void onFail() {
                        view.showNoResponseToast();
                    }
                });
            }
        } else {
            view.showSignInErrorToast();
        }
    }

    private List<APIError> removeDuplicates(List<APIError> apiErrors) {
        LinkedHashSet<APIError> hashSet = new LinkedHashSet<APIError>(apiErrors);
        return new ArrayList<>(hashSet);
    }
}
