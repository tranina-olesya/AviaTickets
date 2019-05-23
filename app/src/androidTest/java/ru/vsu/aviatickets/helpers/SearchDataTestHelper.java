package ru.vsu.aviatickets.helpers;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.CabinClass;
import ru.vsu.aviatickets.ticketssearch.models.FlightType;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.SearchPlace;
import ru.vsu.aviatickets.ui.utils.DateConvert;

public class SearchDataTestHelper {

    private static final int MAX_PASSENGER_COUNT = 8;

    public static SearchData getRandomSearchData() {
        SearchData searchData = new SearchData();

        String origin = "origin" + RandomTestHelper.getRandomInt(0, 100);
        searchData.setOrigin(new SearchPlace(origin, null));

        String destination = "destination" + RandomTestHelper.getRandomInt(0, 100);
        searchData.setDestination(new SearchPlace(destination, null));

        int adultsCount = RandomTestHelper.getRandomInt(1, 4);
        searchData.setAdultsCount(adultsCount);

        int childCount = RandomTestHelper.getRandomInt(0, 4);
        searchData.setChildrenCount(childCount);

        int infantsCount = RandomTestHelper.getRandomInt(0, Math.min(MAX_PASSENGER_COUNT - adultsCount - childCount, adultsCount));
        searchData.setInfantsCount(infantsCount);

        FlightType flightType = RandomTestHelper.getRandomInt(0, 1) == 0 ? FlightType.ROUND : FlightType.ONEWAY;
        searchData.setFlightType(flightType);

        CabinClass cabinClass = RandomTestHelper.getRandomInt(0, 1) == 0 ? CabinClass.BUSINESS : CabinClass.ECONOMY;
        searchData.setCabinClass(cabinClass);

        boolean transfers = RandomTestHelper.getRandomInt(0, 1) != 0;
        searchData.setTransfers(transfers);

        searchData.setInboundDate(DateConvert.getDateFromStringWithSlashes("1/2/2019"));
        searchData.setOutboundDate(DateConvert.getDateFromStringWithSlashes("10/2/2019"));

        return searchData;
    }

    public static List<SearchData> getRandomSearchDataList(int count) {
        List<SearchData> searchDataList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SearchData searchData = getRandomSearchData();
            searchDataList.add(searchData);
        }
        return searchDataList;
    }
}
