package ru.vsu.aviatickets.ui.searchhistory;

import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public interface SearchHistoryContractView {
    void setupAdapter(List<SearchData> searchDataList);

    void notifyRemoved(int index);

    void notifyDataSetChanged(List<SearchData> searchDataList);

    void switchToSearchForm(SearchData searchData);

    void showEmptyMessage();

    void hideEmptyMessage();
}
