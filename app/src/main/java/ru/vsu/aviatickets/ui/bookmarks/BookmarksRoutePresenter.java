package ru.vsu.aviatickets.ui.bookmarks;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public class BookmarksRoutePresenter {
    private BookmarksContractView view;
    private final BookmarksRouteModel model;
    public static List<BookmarkRoute> bookmarkRoutes;

    public BookmarksRoutePresenter(BookmarksRouteModel model) {
        this.model = model;
        bookmarkRoutes = new ArrayList<>();
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
                view.setAdapter(bookmarkRoutes);
                if (BookmarksRoutePresenter.bookmarkRoutes.size() == 0)
                    BookmarksRoutePresenter.bookmarkRoutes.addAll(bookmarkRoutes);
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

    public void delete(int index) {

        BookmarkRoute bookmarkRoute = bookmarkRoutes.get(index);
        model.deleteBookmarkRoute(bookmarkRoute, new BookmarksRouteModel.CompleteCallback() {
            @Override
            public void onComplete() {

                loadBookmarks();
            }
        });
        bookmarkRoutes.remove(index);
    }

    public void itemChosen(SearchData searchData) {

        view.switchToSearchForm(searchData);
    }
}
