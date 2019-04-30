package ru.vsu.aviatickets.ui.searchhistory;

import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.searchhistory.SearchHistoryRepository;

public class SearchHistoryModel {
    private SearchHistoryRepository searchHistoryRepository;

    public SearchHistoryModel() {
        searchHistoryRepository = SearchHistoryRepository.getInstance(App.getInstance().getApplicationContext());
    }

    public void removeItem(int index) {
        searchHistoryRepository.removeSearchData(index);
    }

    public void removeAll() {
        searchHistoryRepository.removeAll();
    }
}
