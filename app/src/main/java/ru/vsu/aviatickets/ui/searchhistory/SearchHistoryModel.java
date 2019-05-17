package ru.vsu.aviatickets.ui.searchhistory;

import java.util.List;

import ru.vsu.aviatickets.searchhistory.SearchHistoryRepository;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public class SearchHistoryModel {
    private SearchHistoryRepository searchHistoryRepository;

    public SearchHistoryModel() {
        searchHistoryRepository = SearchHistoryRepository.getInstance();
    }

    public void removeItem(int index) {
        searchHistoryRepository.removeSearchData(index);
    }

    public void removeAll() {
        searchHistoryRepository.removeAll();
    }

    public List<SearchData> getAll() {
        return searchHistoryRepository.getAllSearchData();
    }

    public int getItemCount() {
        return searchHistoryRepository.getAllSearchData().size();
    }
}
