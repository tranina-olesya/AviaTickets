package ru.vsu.aviatickets.ui.searchform;

import ru.vsu.aviatickets.searchhistory.SearchHistoryRepository;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.providers.IATAProviderAPI;
import ru.vsu.aviatickets.ticketssearch.providers.TicketProviderApi;

public class SearchFormModel {
    private SearchHistoryRepository searchHistoryRepository;
    private IATAProviderAPI iataProviderAPI = new IATAProviderAPI();

    public SearchFormModel() {
        searchHistoryRepository = SearchHistoryRepository.getInstance();
    }

    public void addSearchData(SearchData searchData) {
        searchHistoryRepository.addSearchData(searchData);
    }

    public void searchTickets(SearchData searchData, TicketProviderApi.CityCallback callback) {
        iataProviderAPI.getCityCodes(searchData.getOrigin().getName(), searchData.getDestination().getName(), callback);
    }
}
