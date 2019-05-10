package ru.vsu.aviatickets.ui.tripresults;

import android.os.AsyncTask;

import java.util.List;

import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.bookmarks.logic.AviaTicketsDatabase;
import ru.vsu.aviatickets.bookmarks.logic.BookmarkRouteDao;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ui.bookmarks.BookmarksRouteModel;

public class BookmarkAdditionModel {

    public void addBookmarkRoute(BookmarkRoute value, CompleteCallback callback) {
        BookmarksRouteInsert insert = new BookmarksRouteInsert(callback);
        insert.execute(value);
    }

    static class  BookmarksRouteInsert extends AsyncTask<BookmarkRoute, Void, Void> {

        private final CompleteCallback callback;

        BookmarksRouteInsert(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(BookmarkRoute... params) {
            AviaTicketsDatabase db = App.getInstance().getDatabase();
            BookmarkRouteDao bookmarkRouteDao = db.bookmarkRouteDao();

            for (BookmarkRoute bookmarkRoute : params) {
                bookmarkRouteDao.insert(bookmarkRoute);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    public void findBookmark(SearchData searchData, BookmarkCallback callback){
        BookmarksRouteFind bookmarksRouteFind = new BookmarksRouteFind(callback);
        bookmarksRouteFind.execute(searchData);
    }

    static class BookmarksRouteFind extends AsyncTask<SearchData, Void, BookmarkRoute> {
        private final BookmarkCallback callback;

        BookmarksRouteFind(BookmarkCallback callback) {
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected BookmarkRoute doInBackground(SearchData... params) {
            AviaTicketsDatabase db = App.getInstance().getDatabase();
            BookmarkRouteDao bookmarkRouteDao = db.bookmarkRouteDao();
            SearchData searchData = params[0];
            return bookmarkRouteDao.findByAllParams(searchData.getOrigin(), searchData.getDestination(),
                    searchData.getAdultsCount(), searchData.getChildrenCount(), searchData.getInfantsCount(),
                    searchData.getFlightType().toString(), searchData.getTransfers(), searchData.getCabinClass().toString());
        }

        @Override
        protected void onPostExecute(BookmarkRoute result) {
            callback.onLoad(result);
        }
    }

    public void deleteBookmarkRoute(BookmarkRoute value, BookmarksRouteModel.CompleteCallback callback) {
        BookmarksRouteModel.BookmarksRouteDelete delete = new BookmarksRouteModel.BookmarksRouteDelete(callback);
        delete.execute(value);
    }

    interface CompleteCallback {
        void onComplete();
    }

    interface BookmarkCallback {
        void onLoad(BookmarkRoute bookmarkRoute);
    }
}
