package ru.vsu.aviatickets.ui.bookmarks;

import java.util.List;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;

public interface BookmarksContractView {
    void showBookmarksRoute(List<BookmarkRoute> bookmarkRoutes);
    BookmarkRoute addBookmarkRouteData();
    BookmarkRoute deleteBookmarkRoute();
}
