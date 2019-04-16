package ru.vsu.aviatickets.ui.bookmarks;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.bookmarks.logic.AviaTicketsDatabase;
import ru.vsu.aviatickets.bookmarks.logic.BookmarkRouteDao;

public class BookmarksRouteActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textV;
    private Button but;
    private List<BookmarkRoute> routes;
    private BookmarkRoute id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks_route);
        textView = (TextView) findViewById(R.id.view);
        textV = (TextView) findViewById(R.id.viewDel);

        but = (Button) findViewById(R.id.but);
        routes = new ArrayList<>();
//        but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BookmarkRoute bookmarkRoute = new BookmarkRoute("London", "Moscow", 1,
//                        0, 0, "");
//                BookmarksRouteInsert insert = new BookmarksRouteInsert();
//                insert.execute(bookmarkRoute);
//                BookmarksRouteOut bookmarkDatabase = new BookmarksRouteOut();
//                bookmarkDatabase.execute();
//            }
//        });


        BookmarksRouteOut bookmarkDatabase = new BookmarksRouteOut();
        bookmarkDatabase.execute();

        textView.setText(routes.toString());

        BookmarksRouteGetById getById = new BookmarksRouteGetById();



        BookmarksRouteDelete delete = new BookmarksRouteDelete();
        delete.execute(id);

        textV.setText(routes.toString());

    }

    class BookmarksRouteGetById extends AsyncTask<Long, Void, BookmarkRoute> {
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
            id = result;
        }
    }

    class BookmarksRouteDelete extends AsyncTask<BookmarkRoute, Void, Void> {
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
        }
    }

    class BookmarksRouteInsert extends AsyncTask<BookmarkRoute, Void, Void> {
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
        }
    }

    class BookmarksRouteOut extends AsyncTask<Void, Void, List<BookmarkRoute>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected List<BookmarkRoute> doInBackground(Void... params) {
            AviaTicketsDatabase db = App.getInstance().getDatabase();
            BookmarkRouteDao bookmarkRouteDao = db.bookmarkRouteDao();

            routes = bookmarkRouteDao.getAll();

            return routes;
        }

        @Override
        protected void onPostExecute(List<BookmarkRoute> result) {
            super.onPostExecute(result);
            textView.setText(result.toString());
        }
    }
}
