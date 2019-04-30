package ru.vsu.aviatickets.ui.searchhistory;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public interface SearchHistoryContractView {
    void setupAdapter();

    void notifyRemoved(int index);

    void notifyRemovedAll();

    void switchToSearchForm(SearchData searchData);
}
