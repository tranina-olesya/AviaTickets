package ru.vsu.aviatickets.ui.tripresults;

import android.os.AsyncTask;

import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.bookmarks.logic.AviaTicketsDatabase;
import ru.vsu.aviatickets.bookmarks.logic.BookmarkRouteDao;

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

    interface CompleteCallback {
        void onComplete();
    }
}
