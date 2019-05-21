package ru.vsu.aviatickets.ui.bookmarks;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;

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
        model.outBookmarksRoute(new BookmarksRouteModel.OutBookmarkCallback() {
            @Override
            public void onLoad(List<BookmarkRoute> bookmarkRoutes) {
                if (bookmarkRoutes == null || bookmarkRoutes.isEmpty())
                    view.showEmptyMessage();
                else {
                    view.hideEmptyMessage();
                    BookmarksRoutePresenter.bookmarkRoutes = bookmarkRoutes;
                    view.setAdapter(bookmarkRoutes);
                }
            }
        });
    }


    public void delete(int index) {
        BookmarkRoute bookmarkRoute = bookmarkRoutes.get(index);
        model.deleteBookmarkRoute(bookmarkRoute, new BookmarksRouteModel.CompleteCallback() {
            @Override
            public void onComplete() {
                bookmarkRoutes.remove(index);
                view.itemRemoved(index);
                if (bookmarkRoutes.isEmpty()) {
                    view.showEmptyMessage();
                }
            }
        });
    }

    public void itemChosen(SearchData searchData) {
        view.switchToSearchForm(searchData);
    }
}
