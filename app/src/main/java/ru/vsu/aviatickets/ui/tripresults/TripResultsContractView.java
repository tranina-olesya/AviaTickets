package ru.vsu.aviatickets.ui.tripresults;

import java.util.List;

import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.entities.tripmodels.Trip;

public interface TripResultsContractView {
    void showTrips();

    void showProgress();

    void hideProgress();

    void ticketsNotFound();

    void noResponse();

    BookmarkRoute addBookmarkRouteData();

    void hideGroupTripResults();

    void showGroupTripResults();

    void bookmarkAdded();

    void bookmarkDeleted();

    SearchData getSearchData();

    void disableBookmarksButton();

    void enableBookmarksButton();

    void setLoadedTrips(List<Trip> loadedTrips);

    void loadMore();

    void showNoResponseToast();

    void showSignInErrorToast();
}
