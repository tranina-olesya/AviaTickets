package ru.vsu.aviatickets.ui.bookmarks;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.providers.BookmarkAPIProvider;

public class BookmarksRoutePresenter {
    public static List<BookmarkRoute> bookmarkRoutes;
    private final BookmarksRouteModel model;
    private BookmarksContractView view;

    public BookmarksRoutePresenter(BookmarksRouteModel model) {
        this.model = model;
        bookmarkRoutes = new ArrayList<>();
    }

    public void attachView(BookmarksRouteFragment fragment) {
        view = fragment;
    }

    public void viewIsReady() {
        loadBookmarks();
    }

    public void loadBookmarks() {
        view.hideEmptyMessage();
        view.hideBookmarkList();
        String userCode = App.getUserCode();
        if (userCode != null) {
            view.showLoading();
            view.hideSignInButton();
            model.outBookmarksRoute(userCode, new BookmarkAPIProvider.BookmarksCallback() {
                @Override
                public void onComplete(List<BookmarkRoute> bookmarkRoutes) {
                    if (bookmarkRoutes == null || bookmarkRoutes.isEmpty()) {
                        view.hideBookmarkList();
                        view.showEmptyMessage();
                    } else {
                        view.hideEmptyMessage();
                        view.showBookmarkList();
                        BookmarksRoutePresenter.bookmarkRoutes = bookmarkRoutes;
                        view.setAdapter(bookmarkRoutes);
                    }
                    view.hideUpdateButton();
                    view.hideLoading();
                }

                @Override
                public void onFail() {
                    view.hideLoading();
                    view.showEmptyMessage();
                    view.showUpdateButton();
                    view.showNoResponseToast();
                }
            });
        } else  {
            view.showSignInButton();
        }
    }


    public void delete(int index) {
        String userCode = App.getUserCode();
        if (userCode != null) {
            BookmarkRoute bookmarkRoute = bookmarkRoutes.get(index);
            model.deleteBookmarkRoute(userCode, bookmarkRoute, new CompleteCallback() {
                @Override
                public void onComplete() {
                    bookmarkRoutes.remove(index);
                    view.itemRemoved(index);
                    if (bookmarkRoutes.isEmpty()) {
                        view.hideBookmarkList();
                        view.showEmptyMessage();
                    }
                }

                @Override
                public void onFail() {
                    view.showNoResponseToast();
                }
            });
        }
    }

    public void itemChosen(SearchData searchData) {
        view.switchToSearchForm(searchData);
    }
}
