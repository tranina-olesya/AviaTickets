package ru.vsu.aviatickets.ui.searchhistory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.searchhistory.SearchHistoryRepository;

public class SearchHistoryFragment extends Fragment {

    private RecyclerView recyclerView;

    public SearchHistoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_history, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        SearchHistoryAdapter adapter = new SearchHistoryAdapter(getContext(), SearchHistoryRepository.getAllSearchData());
        recyclerView.setAdapter(adapter);
        return view;
    }

}
