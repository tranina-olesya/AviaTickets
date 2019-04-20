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

public class BookmarksRouteActivity extends AppCompatActivity implements BookmarksContractView {
    private TextView textView;
    private TextView textV;
    private Button but;
    private BookmarkRoute id;
    BookmarksRoutePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks_route);
        textView = (TextView) findViewById(R.id.view);
        textV = (TextView) findViewById(R.id.viewDel);

        but = (Button) findViewById(R.id.but);

        BookmarksRouteModel model = new BookmarksRouteModel();
        presenter = new BookmarksRoutePresenter(model);
        presenter.attachView(this);

        presenter.viewIsReady();

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.delete();
            }
        });


    }

    public void showBookmarksRoute(List<BookmarkRoute> bookmarkRoutes)
    {
        textView.setText(bookmarkRoutes.toString());
    }

    public BookmarkRoute addBookmarkRouteData(){
        BookmarkRoute bookmarkRoute = new BookmarkRoute("Madrid","London",
                                            1,0,0,"");
        return bookmarkRoute;
    }

    public BookmarkRoute deleteBookmarkRoute(){
        BookmarkRoute bookmarkRoute = new BookmarkRoute("Madrid","London",
                1,0,0,"");
               bookmarkRoute.setId((long)1);

        return bookmarkRoute;
    }





}
