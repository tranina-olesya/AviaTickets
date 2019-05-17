package ru.vsu.aviatickets.ui.searchhistory;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public class SearchHistoryPresenter {
    private SearchHistoryContractView view;
    private SearchHistoryModel model;

    public SearchHistoryPresenter(SearchHistoryModel model) {
        this.model = model;
    }

    public void attachView(SearchHistoryContractView searchHistoryContractView) {
        view = searchHistoryContractView;
    }

    public void viewIsReady() {
        List<SearchData> searchDataList = model.getAll();
        if (searchDataList == null || searchDataList.isEmpty())
            view.showEmptyMessage();
        else {
            view.hideEmptyMessage();
            view.setupAdapter(searchDataList);
        }
    }

    public void removeItem(int index) {
        model.removeItem(index);
        view.notifyRemoved(index);
        if (model.getItemCount() == 0) {
            view.showEmptyMessage();
        }
    }

    public void itemChosen(SearchData searchData) {
        view.switchToSearchForm(searchData);
    }

    public void clearHistory() {
        model.removeAll();
        view.notifyDataSetChanged(new ArrayList<>());
        view.showEmptyMessage();
    }
}
