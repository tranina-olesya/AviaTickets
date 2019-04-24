package ru.vsu.aviatickets.ui.bookmarks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;


public class BookmarksRouteActivity extends AppCompatActivity implements BookmarksContractView {

    BookmarksRoutePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks_route);

        BookmarksRouteModel model = new BookmarksRouteModel();
        presenter = new BookmarksRoutePresenter(model);
        presenter.attachView(this);

        presenter.viewIsReady();

    }

    public void showBookmarksRoute(List<BookmarkRoute> bookmarkRoutes)
    {

    }

    public BookmarkRoute addBookmarkRouteData(){

        return null;
    }

    public BookmarkRoute deleteBookmarkRoute(){


        return null;
    }





}
