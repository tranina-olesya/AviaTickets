package ru.vsu.aviatickets.ui.bookmarks;

import java.util.List;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;

public class BookmarksRoutePresenter {
    private BookmarksContractView view;
    private final BookmarksRouteModel model;

    public BookmarksRoutePresenter(BookmarksRouteModel model) {
        this.model = model;
    }

    public void attachView(BookmarksRouteFragment activity) {
        view = activity;
    }

    public void detachView() {
        view = null;
    }


    public void viewIsReady() {
        loadBookmarks();
    }

    public void loadBookmarks() {
        model.outBookmarksRoute(new BookmarksRouteModel.OutBookmarkCallback() {
            @Override
            public void onLoad(List<BookmarkRoute> bookmarkRoutes) {
                view.showBookmarksRoute(bookmarkRoutes);
            }
        });
    }

    public void insert() {
        BookmarkRoute bookmarkRoute = view.addBookmarkRouteData();

        model.addBookmarkRoute(bookmarkRoute, new BookmarksRouteModel.CompleteCallback() {
            @Override
            public void onComplete() {

                loadBookmarks();
            }
        });
    }
    public void delete() {
        BookmarkRoute bookmarkRoute = view.deleteBookmarkRoute();

        model.deleteBookmarkRoute(bookmarkRoute, new BookmarksRouteModel.CompleteCallback() {
            @Override
            public void onComplete() {

                loadBookmarks();
            }
        });
    }
}
