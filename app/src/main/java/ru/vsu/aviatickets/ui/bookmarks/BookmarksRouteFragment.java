package ru.vsu.aviatickets.ui.bookmarks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.ui.main.MainActivity;


public class BookmarksRouteFragment extends Fragment implements BookmarksContractView {
    private RecyclerView recyclerView;
    private BookmarksRoutePresenter presenter;
    private BookmarksAdapter adapter;
    private TextView noBookmarksTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmarks_route, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        noBookmarksTextView = view.findViewById(R.id.noBookmarks);
        BookmarksRouteModel model = new BookmarksRouteModel();
        presenter = new BookmarksRoutePresenter(model);
        presenter.attachView(this);
        presenter.viewIsReady();
        return view;
    }

    @Override
    public void setAdapter(List<BookmarkRoute> bookmarkRoutes){
        adapter = new BookmarksAdapter(getContext(), bookmarkRoutes);
        adapter.setPresenter(presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void switchToSearchForm(SearchData searchData) {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null)
            activity.setSearchFormFragmentWithSearchData(searchData);
    }

    @Override
    public void itemRemoved(int index) {
        adapter.notifyItemRemoved(index);
    }

    @Override
    public void showEmptyMessage() {
        noBookmarksTextView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideEmptyMessage() {
        noBookmarksTextView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
