package ru.vsu.aviatickets.ui.bookmarks;

import android.os.AsyncTask;

import java.util.List;

import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.bookmarks.logic.AviaTicketsDatabase;
import ru.vsu.aviatickets.bookmarks.logic.BookmarkRouteDao;

public class BookmarksRouteModel {

    public void outBookmarksRoute(OutBookmarkCallback callback) {
        BookmarksRouteOut out = new BookmarksRouteOut(callback);
        out.execute();
    }

    public void addBookmarkRoute(BookmarkRoute value, CompleteCallback callback) {
        BookmarksRouteInsert insert = new BookmarksRouteInsert(callback);
        insert.execute(value);
    }

    public void deleteBookmarkRoute(BookmarkRoute value, CompleteCallback callback) {
        BookmarksRouteDelete delete = new BookmarksRouteDelete(callback);
        delete.execute(value);
    }

    public void getBookmarkById(Long id, OutBookmarkCallback callback){
        BookmarksRouteGetById getById = new BookmarksRouteGetById(callback);
        getById.execute(id);
    }

    interface OutBookmarkCallback {
        void onLoad(List<BookmarkRoute> bookmarkRoutes);
    }

    interface CompleteCallback {
        void onComplete();
    }

    class BookmarksRouteInsert extends AsyncTask<BookmarkRoute, Void, Void> {

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

    class BookmarksRouteOut extends AsyncTask<Void, Void, List<BookmarkRoute>> {

        private final OutBookmarkCallback callback;

        BookmarksRouteOut(OutBookmarkCallback callback) {
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected List<BookmarkRoute> doInBackground(Void... params) {
            AviaTicketsDatabase db = App.getInstance().getDatabase();
            BookmarkRouteDao bookmarkRouteDao = db.bookmarkRouteDao();

            return bookmarkRouteDao.getAll();
        }

        @Override
        protected void onPostExecute(List<BookmarkRoute> result) {
            super.onPostExecute(result);
            if (callback != null) {
                callback.onLoad(result);
            }
        }
    }

    class BookmarksRouteDelete extends AsyncTask<BookmarkRoute, Void, Void> {

        private final CompleteCallback callback;

        BookmarksRouteDelete(CompleteCallback callback) {
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
                bookmarkRouteDao.delete(bookmarkRoute);
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

    class BookmarksRouteGetById extends AsyncTask<Long, Void, BookmarkRoute> {
        private final OutBookmarkCallback callback;

        BookmarksRouteGetById(OutBookmarkCallback callback) {
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected BookmarkRoute doInBackground(Long... params) {
            AviaTicketsDatabase db = App.getInstance().getDatabase();
            BookmarkRouteDao bookmarkRouteDao = db.bookmarkRouteDao();

            for (Long id : params) {
                return bookmarkRouteDao.getById(id);
            }
            return null;
        }

        @Override
        protected void onPostExecute(BookmarkRoute result) {
            super.onPostExecute(result);

        }
    }
}
