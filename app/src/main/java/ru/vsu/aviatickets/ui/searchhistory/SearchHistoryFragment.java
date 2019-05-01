package ru.vsu.aviatickets.ui.searchhistory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ui.main.MainActivity;

public class SearchHistoryFragment extends Fragment implements SearchHistoryContractView {

    private RecyclerView recyclerView;
    private Button clearButton;
    private SearchHistoryPresenter presenter;
    private SearchHistoryAdapter adapter;

    public SearchHistoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_history, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        clearButton = view.findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clearHistory();
            }
        });

        presenter = new SearchHistoryPresenter(new SearchHistoryModel());
        presenter.attachView(this);
        presenter.viewIsReady();

        return view;
    }

    @Override
    public void setupAdapter(List<SearchData> searchDataList) {
        adapter = new SearchHistoryAdapter(getContext(), searchDataList);
        adapter.setPresenter(presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void notifyRemoved(int index) {
        adapter.notifyItemRemoved(index);
    }

    @Override
    public void notifyDataSetChanged(List<SearchData> searchDataList) {
        adapter.setSearchDataList(searchDataList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void switchToSearchForm(SearchData searchData) {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null)
            activity.setSearchFormFragmentWithSearchData(searchData);
    }
}
