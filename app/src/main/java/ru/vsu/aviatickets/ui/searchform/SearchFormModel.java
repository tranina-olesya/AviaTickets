package ru.vsu.aviatickets.ui.searchform;

import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.entities.SearchHistoryEntry;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.providers.SearchHistoryAPIProvider;
import ru.vsu.aviatickets.api.providers.TripAPIProvider;

public class SearchFormModel {

    private TripAPIProvider tripAPIProvider;
    private SearchHistoryAPIProvider searchHistoryAPIProvider;

    public SearchFormModel() {
        this.tripAPIProvider = new TripAPIProvider();
        this.searchHistoryAPIProvider = new SearchHistoryAPIProvider();
    }

    public void addSearchData(SearchHistoryEntry searchHistoryEntry, CompleteCallback callback) {
        searchHistoryAPIProvider.addSearchHistoryEntry(searchHistoryEntry, callback);
    }

    public void searchCities(SearchData searchData, TripAPIProvider.CityCallback callback) {
        tripAPIProvider.getCityCode(searchData.getOrigin().getName(), searchData.getDestination().getName(), callback);
//        iataProviderAPI.getCityCodes(searchData.getOrigin().getName(), searchData.getDestination().getName(), callback);
    }
}
