package ru.vsu.aviatickets.ui.searchhistory;

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
}
