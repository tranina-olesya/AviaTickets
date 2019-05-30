package ru.vsu.aviatickets.ui.bookmarks;

import java.util.List;

import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;

public interface BookmarksContractView {
    void setAdapter(List<BookmarkRoute> bookmarkRoutes);

    void switchToSearchForm(SearchData searchData);

    void itemRemoved(int index);

    void showEmptyMessage();

    void hideEmptyMessage();

    void showBookmarkList();

    void hideBookmarkList();

    void showNoResponseToast();

    void showLoading();

    void hideLoading();

    void showUpdateButton();

    void hideUpdateButton();

    void showSignInButton();

    void hideSignInButton();
}
