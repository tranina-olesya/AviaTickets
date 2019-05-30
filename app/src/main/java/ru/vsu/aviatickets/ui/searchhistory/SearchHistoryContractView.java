package ru.vsu.aviatickets.ui.searchhistory;

import java.util.List;

import ru.vsu.aviatickets.api.entities.SearchHistoryEntry;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;

public interface SearchHistoryContractView {
    void setupAdapter(List<SearchHistoryEntry> searchDataList);

    void notifyRemoved(int index);

    void notifyDataSetChanged(List<SearchHistoryEntry> searchHistoryEntries);

    void switchToSearchForm(SearchData searchData);

    void showEmptyMessage();

    void hideEmptyMessage();

    void showSearchHistoryList();

    void hideSearchHistoryList();

    void showNoResponseToast();

    void showLoading();

    void hideLoading();

    void showUpdateButton();

    void hideUpdateButton();

    void showSignInButton();

    void hideSignInButton();
}
