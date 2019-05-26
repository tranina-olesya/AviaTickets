package ru.vsu.aviatickets.ui.tripresults;

import android.os.AsyncTask;

import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.dto.request.BookmarkRequestDTO;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.providers.BookmarkAPIProvider;
import ru.vsu.aviatickets.ui.bookmarks.BookmarksRouteModel;

public class BookmarkAdditionModel {
    private BookmarkAPIProvider bookmarkAPIProvider;

    public BookmarkAdditionModel() {
        this.bookmarkAPIProvider = new BookmarkAPIProvider();
    }
    public void addBookmarkRoute(BookmarkRoute value, CompleteCallback callback) {
        bookmarkAPIProvider.addBookmark(value, callback);
    }

    public void findBookmark(SearchData searchData, BookmarkAPIProvider.BookmarkCallback callback) {
        bookmarkAPIProvider.findBookmark(new BookmarkRequestDTO(App.getUserCode(), searchData), callback);
    }

    public void deleteBookmarkRoute(BookmarkRoute value, CompleteCallback callback) {
        bookmarkAPIProvider.deleteBookmark(value.getId(), callback);
    }

}
