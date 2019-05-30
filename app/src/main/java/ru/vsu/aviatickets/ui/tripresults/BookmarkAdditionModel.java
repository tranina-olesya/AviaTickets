package ru.vsu.aviatickets.ui.tripresults;

import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.dto.request.BookmarkRequestDTO;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.providers.BookmarkAPIProvider;

public class BookmarkAdditionModel {
    private BookmarkAPIProvider bookmarkAPIProvider;

    public BookmarkAdditionModel() {
        this.bookmarkAPIProvider = new BookmarkAPIProvider();
    }

    public void addBookmarkRoute(String userCode, BookmarkRoute value, CompleteCallback callback) {
        bookmarkAPIProvider.addBookmark(userCode, value, callback);
    }

    public void findBookmark(String userCode, SearchData searchData, BookmarkAPIProvider.BookmarkCallback callback) {
        bookmarkAPIProvider.findBookmark(new BookmarkRequestDTO(userCode, searchData), callback);
    }

    public void deleteBookmarkRoute(BookmarkRoute value, CompleteCallback callback) {
        bookmarkAPIProvider.deleteBookmark(value.getId(), callback);
    }

}
