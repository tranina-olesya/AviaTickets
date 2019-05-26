package ru.vsu.aviatickets.ui.searchhistory;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.entities.SearchHistoryEntry;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchPlace;
import ru.vsu.aviatickets.api.providers.SearchHistoryAPIProvider;

public class SearchHistoryPresenter {
    private SearchHistoryContractView view;
    private SearchHistoryModel model;

    private List<SearchHistoryEntry> searchHistoryEntries;

    public SearchHistoryPresenter(SearchHistoryModel model) {
        this.model = model;
    }

    public void attachView(SearchHistoryContractView searchHistoryContractView) {
        view = searchHistoryContractView;
        searchHistoryEntries = new ArrayList<>();
    }

    public void viewIsReady() {
        loadData();
    }

    private void loadData() {
        model.getAll(new SearchHistoryAPIProvider.SearchHistoryCallback() {
            @Override
            public void onLoad(List<SearchHistoryEntry> searchHistoryEntrihistoryEntryList) {
                searchHistoryEntries = searchHistoryEntrihistoryEntryList;
                if (searchHistoryEntries == null || searchHistoryEntries.isEmpty())
                    view.showEmptyMessage();
                else {
                    view.hideEmptyMessage();
                    view.setupAdapter(searchHistoryEntries);
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void removeItem(int index) {
        SearchHistoryEntry searchHistoryEntry = searchHistoryEntries.get(index);
        model.removeItem(searchHistoryEntry.getId(), new CompleteCallback() {
            @Override
            public void onComplete() {
                searchHistoryEntries.remove(index);
                view.notifyRemoved(index);
                if (searchHistoryEntries.size() == 0) {
                    view.showEmptyMessage();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void itemChosen(SearchHistoryEntry searchHistoryEntry) {
        SearchData searchData = new SearchData(new SearchPlace(searchHistoryEntry.getOrigin()),
                new SearchPlace(searchHistoryEntry.getDestination()), searchHistoryEntry.getOutboundDate(),
                searchHistoryEntry.getInboundDate(), searchHistoryEntry.getAdultsCount(),
                searchHistoryEntry.getChildrenCount(), searchHistoryEntry.getInfantsCount(),
                searchHistoryEntry.getFlightType(), searchHistoryEntry.getTransfers(),
                searchHistoryEntry.getCabinClass());
        view.switchToSearchForm(searchData);
    }

    public void clearHistory() {
        model.removeAll(new CompleteCallback() {
            @Override
            public void onComplete() {
                view.notifyDataSetChanged(new ArrayList<>());
                view.showEmptyMessage();
            }

            @Override
            public void onFail() {

            }
        });
    }
}
