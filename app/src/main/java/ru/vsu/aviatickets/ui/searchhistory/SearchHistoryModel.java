package ru.vsu.aviatickets.ui.searchhistory;

import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.providers.SearchHistoryAPIProvider;

public class SearchHistoryModel {

    private SearchHistoryAPIProvider searchHistoryAPIProvider;

    public SearchHistoryModel() {
        this.searchHistoryAPIProvider = new SearchHistoryAPIProvider();
    }

    public void removeItem(String userCode, Long id, CompleteCallback callback) {
        searchHistoryAPIProvider.deleteSearchHistoryEntry(userCode, id, callback);
    }

    public void removeAll(String userCode, CompleteCallback callback) {
        searchHistoryAPIProvider.deleteAllSearchHistory(userCode, callback);
    }

    public void getAll(String userCode, SearchHistoryAPIProvider.SearchHistoryCallback callback) {
        searchHistoryAPIProvider.getSearchHistory(userCode, callback);
    }
}
