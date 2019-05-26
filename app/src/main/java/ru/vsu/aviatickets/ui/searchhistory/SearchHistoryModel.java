package ru.vsu.aviatickets.ui.searchhistory;

import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.providers.SearchHistoryAPIProvider;

public class SearchHistoryModel {

    private SearchHistoryAPIProvider searchHistoryAPIProvider;

    public SearchHistoryModel() {
        this.searchHistoryAPIProvider = new SearchHistoryAPIProvider();
    }

    public void removeItem(Long id, CompleteCallback callback) {
        searchHistoryAPIProvider.deleteSearchHistoryEntry(id, callback);
    }

    public void removeAll(CompleteCallback callback) {
        searchHistoryAPIProvider.deleteAllSearchHistory(callback);
    }

    public void getAll(SearchHistoryAPIProvider.SearchHistoryCallback callback) {
        searchHistoryAPIProvider.getSearchHistory(callback);
    }
}
