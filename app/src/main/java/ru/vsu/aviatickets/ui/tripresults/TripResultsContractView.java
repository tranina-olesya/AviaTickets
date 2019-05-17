package ru.vsu.aviatickets.ui.tripresults;

import java.util.List;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Trip;

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
}
