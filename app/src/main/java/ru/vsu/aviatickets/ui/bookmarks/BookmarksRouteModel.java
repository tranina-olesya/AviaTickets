package ru.vsu.aviatickets.ui.bookmarks;

import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.providers.BookmarkAPIProvider;

public class BookmarksRouteModel {
    private BookmarkAPIProvider bookmarkAPIProvider;

    public BookmarksRouteModel() {
        bookmarkAPIProvider = new BookmarkAPIProvider();
    }

    public void outBookmarksRoute(String userCode, BookmarkAPIProvider.BookmarksCallback callback) {
        bookmarkAPIProvider.getBookmarks(userCode, callback);
    }

    public void deleteBookmarkRoute(BookmarkRoute value, CompleteCallback callback) {
        bookmarkAPIProvider.deleteBookmark(value.getId(), callback);
    }

}
