package ru.vsu.aviatickets.ui.searchhistory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.searchhistory.SearchHistoryRepository;
import ru.vsu.aviatickets.ui.tripresults.TripResultsPresenter;

public class SearchHistoryFragment extends Fragment implements SearchHistoryContractView {

    private RecyclerView recyclerView;
    private SearchHistoryPresenter presenter;
    private SearchHistoryAdapter adapter;

    public SearchHistoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_history, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        presenter = new SearchHistoryPresenter(new SearchHistoryModel());
        presenter.attachView(this);
        presenter.viewIsReady();
        return view;
    }

    @Override
    public void setupAdapter() {
        adapter = new SearchHistoryAdapter(getContext(), SearchHistoryRepository.getInstance(getContext()).getAllSearchData());
        adapter.setPresenter(presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void notifyRemoved(int index) {
        adapter.notifyItemRemoved(index);
    }
}
