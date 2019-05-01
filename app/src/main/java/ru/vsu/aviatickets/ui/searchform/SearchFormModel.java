package ru.vsu.aviatickets.ui.searchform;

import ru.vsu.aviatickets.searchhistory.SearchHistoryRepository;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public class SearchFormModel {
    private SearchHistoryRepository searchHistoryRepository;

    public SearchFormModel() {
        searchHistoryRepository = SearchHistoryRepository.getInstance();
    }

    public void addSearchData(SearchData searchData) {
        searchHistoryRepository.addSearchData(searchData);
    }
}
