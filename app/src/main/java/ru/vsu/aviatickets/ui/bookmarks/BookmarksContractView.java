package ru.vsu.aviatickets.ui.bookmarks;

import java.util.List;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public interface BookmarksContractView {
    void setAdapter(List<BookmarkRoute> bookmarkRoutes);
    void switchToSearchForm(SearchData searchData);
    BookmarkRoute addBookmarkRouteData();

}
