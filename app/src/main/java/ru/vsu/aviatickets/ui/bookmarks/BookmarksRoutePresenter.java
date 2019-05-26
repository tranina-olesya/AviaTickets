package ru.vsu.aviatickets.ui.bookmarks;

import java.util.ArrayList;
import java.util.List;

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
        model.outBookmarksRoute(new BookmarkAPIProvider.BookmarksCallback() {
            @Override
            public void onComplete(List<BookmarkRoute> bookmarkRoutes) {
                if (bookmarkRoutes == null || bookmarkRoutes.isEmpty())
                    view.showEmptyMessage();
                else {
                    view.hideEmptyMessage();
                    BookmarksRoutePresenter.bookmarkRoutes = bookmarkRoutes;
                    view.setAdapter(bookmarkRoutes);
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public void delete(int index) {
        BookmarkRoute bookmarkRoute = bookmarkRoutes.get(index);
        model.deleteBookmarkRoute(bookmarkRoute, new CompleteCallback() {
            @Override
            public void onComplete() {
                bookmarkRoutes.remove(index);
                view.itemRemoved(index);
                if (bookmarkRoutes.isEmpty()) {
                    view.showEmptyMessage();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void itemChosen(SearchData searchData) {
        view.switchToSearchForm(searchData);
    }
}
