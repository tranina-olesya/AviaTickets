package ru.vsu.aviatickets.ui.searchhistory;

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
        view.setupAdapter();
    }

    public void removeItem(int index) {
        model.removeItem(index);
        view.notifyRemoved(index);
    }

    public void itemChosen(SearchData searchData) {
        view.switchToSearchForm(searchData);
    }
}
