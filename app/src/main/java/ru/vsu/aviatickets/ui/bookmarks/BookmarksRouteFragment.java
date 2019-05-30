package ru.vsu.aviatickets.ui.bookmarks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.ui.main.MainActivity;
import ru.vsu.aviatickets.ui.signin.SignInActivity;


public class BookmarksRouteFragment extends Fragment implements BookmarksContractView {
    private RecyclerView recyclerView;
    private BookmarksRoutePresenter presenter;
    private BookmarksAdapter adapter;
    private TextView noBookmarksTextView;
    private ProgressBar progressBar;
    private TextView signInText;
    private Button singInButton;
    private TextView loadingBookmarkText;
    private Button updateButton;

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
        progressBar = view.findViewById(R.id.progress);
        updateButton = view.findViewById(R.id.updateButton);
        signInText = view.findViewById(R.id.signInText);
        singInButton = view.findViewById(R.id.signInButton);
        loadingBookmarkText = view.findViewById(R.id.loadingText);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadBookmarks();
            }
        });
        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    startActivity(new Intent(activity, SignInActivity.class));
                }
            }
        });
        BookmarksRouteModel model = new BookmarksRouteModel();
        presenter = new BookmarksRoutePresenter(model);
        presenter.attachView(this);
        presenter.viewIsReady();
        return view;
    }

    @Override
    public void setAdapter(List<BookmarkRoute> bookmarkRoutes) {
        if (getActivity() != null) {
            adapter = new BookmarksAdapter(getContext(), bookmarkRoutes);
            adapter.setPresenter(presenter);
            recyclerView.setAdapter(adapter);
        }
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

    @Override
    public void showBookmarkList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBookmarkList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showNoResponseToast() {
        if (getActivity() != null) {
            Toast toast = Toast.makeText(getContext(), R.string.responseError, Toast.LENGTH_LONG);
            toast.setMargin(0, 0.1f);
            toast.show();
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        loadingBookmarkText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        loadingBookmarkText.setVisibility(View.GONE);
    }

    @Override
    public void showUpdateButton() {
        updateButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUpdateButton() {
        updateButton.setVisibility(View.GONE);
    }

    @Override
    public void showSignInButton() {
        singInButton.setVisibility(View.VISIBLE);
        signInText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSignInButton() {
        signInText.setVisibility(View.GONE);
        singInButton.setVisibility(View.GONE);
    }
}
