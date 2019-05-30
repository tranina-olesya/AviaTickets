package ru.vsu.aviatickets.ui.searchhistory;


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
import ru.vsu.aviatickets.api.entities.SearchHistoryEntry;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.ui.main.MainActivity;
import ru.vsu.aviatickets.ui.signin.SignInActivity;

public class SearchHistoryFragment extends Fragment implements SearchHistoryContractView {

    private RecyclerView recyclerView;
    private Button clearButton;
    private SearchHistoryPresenter presenter;
    private SearchHistoryAdapter adapter;
    private TextView noSearchHistoryTextView;
    private ProgressBar progressBar;
    private TextView loadingHistoryText;
    private Button updateButton;
    private TextView signInText;
    private Button singInButton;

    public SearchHistoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_history, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        clearButton = view.findViewById(R.id.clearButton);
        noSearchHistoryTextView = view.findViewById(R.id.noSearchHistory);
        progressBar = view.findViewById(R.id.progress);
        updateButton = view.findViewById(R.id.updateButton);
        loadingHistoryText = view.findViewById(R.id.loadingText);
        signInText = view.findViewById(R.id.signInText);
        singInButton = view.findViewById(R.id.signInButton);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clearHistory();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadData();
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
        presenter = new SearchHistoryPresenter(new SearchHistoryModel());
        presenter.attachView(this);
        presenter.viewIsReady();

        return view;
    }

    @Override
    public void setupAdapter(List<SearchHistoryEntry> searchHistoryEntries) {
        if (getActivity() != null) {
            adapter = new SearchHistoryAdapter(getContext(), searchHistoryEntries);
            adapter.setPresenter(presenter);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void notifyRemoved(int index) {
        adapter.notifyItemRemoved(index);
    }

    @Override
    public void notifyDataSetChanged(List<SearchHistoryEntry> searchHistoryEntries) {
        adapter.setSearchDataList(searchHistoryEntries);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void switchToSearchForm(SearchData searchData) {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null)
            activity.setSearchFormFragmentWithSearchData(searchData);
    }

    @Override
    public void showEmptyMessage() {
        noSearchHistoryTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyMessage() {
        noSearchHistoryTextView.setVisibility(View.GONE);
    }

    @Override
    public void showSearchHistoryList() {
        recyclerView.setVisibility(View.VISIBLE);
        clearButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSearchHistoryList() {
        recyclerView.setVisibility(View.GONE);
        clearButton.setVisibility(View.GONE);
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
        loadingHistoryText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        loadingHistoryText.setVisibility(View.GONE);
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
