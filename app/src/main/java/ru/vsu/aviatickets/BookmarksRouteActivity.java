package ru.vsu.aviatickets;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.bookmarks.logic.AviaTicketsDatabase;
import ru.vsu.aviatickets.bookmarks.logic.BookmarkRouteDao;

public class BookmarksRouteActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks_route);
        textView = (TextView) findViewById(R.id.textView);
        BookmarkDatabase bookmarkDatabase = new BookmarkDatabase();
        bookmarkDatabase.execute();
    }

    class BookmarkDatabase extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {
            AviaTicketsDatabase db = App.getInstance().getDatabase();
            BookmarkRouteDao bookmarkRouteDao = db.bookmarkRouteDao();
            BookmarkRoute bookmarkRoute = new BookmarkRoute("Voronezh", "Moscow", 1,
                    0, 0, "");
           // bookmarkRouteDao.insert(bookmarkRoute);
            List<BookmarkRoute> list = bookmarkRouteDao.getAll();

            String res = list.toString();


            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            textView.setText(result);
        }
    }
}
