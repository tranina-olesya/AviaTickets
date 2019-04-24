package ru.vsu.aviatickets.ui.bookmarks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;


public class BookmarksRouteFragment extends Fragment implements BookmarksContractView {

    private BookmarksRoutePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmarks_route, container, false);
        BookmarksRouteModel model = new BookmarksRouteModel();
        presenter = new BookmarksRoutePresenter(model);
        presenter.attachView(this);
        presenter.viewIsReady();
        return view;
    }

    @Override
    public void showBookmarksRoute(List<BookmarkRoute> bookmarkRoutes) {
    }

    @Override
    public BookmarkRoute addBookmarkRouteData() {
        return null;
    }

    @Override
    public BookmarkRoute deleteBookmarkRoute() {
        return null;
    }
}
