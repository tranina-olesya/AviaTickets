package ru.vsu.aviatickets;


import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import ru.vsu.aviatickets.helpers.SearchDataTestHelper;
import ru.vsu.aviatickets.searchhistory.SearchHistoryRepository;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class SearchHistoryTest {

    private static final String SHARED_PREFS_NAME = "test_prefs";
    private SearchHistoryRepository searchHistoryRepository;

    @Before
    public void init() {
        searchHistoryRepository = SearchHistoryRepository.getInstance(SHARED_PREFS_NAME);
    }

    @After
    public void clear() {
        searchHistoryRepository.removeAll();
    }

    @Test
    public void searchHistory_shouldAddAndReadSearchData() {
        SearchData searchData = SearchDataTestHelper.getRandomSearchData();
        searchHistoryRepository.addSearchData(searchData);

        List<SearchData> allSearchData = searchHistoryRepository.getAllSearchData();

        assertEquals(searchData, allSearchData.get(0));
    }

    @Test
    public void searchHistory_shouldAddAndReadManySearchData() {
        List<SearchData> searchDataList = SearchDataTestHelper.getRandomSearchDataList(5);

        for (SearchData searchData : searchDataList) {
            searchHistoryRepository.addSearchData(searchData);
        }

        List<SearchData> allSearchData = searchHistoryRepository.getAllSearchData();

        assertEquals(searchDataList.size(), allSearchData.size());
    }

    @Test
    public void searchHistory_shouldAddAndRemoveSearchData() {
        SearchData searchData = SearchDataTestHelper.getRandomSearchData();

        searchHistoryRepository.addSearchData(searchData);
        searchHistoryRepository.removeSearchData(0);

        List<SearchData> allSearchData = searchHistoryRepository.getAllSearchData();

        assertTrue(allSearchData.isEmpty());
    }

    @Test
    public void searchHistory_shouldAddAndRemoveManySearchData() {
        List<SearchData> searchDataList = SearchDataTestHelper.getRandomSearchDataList(5);

        for (SearchData searchData : searchDataList) {
            searchHistoryRepository.addSearchData(searchData);
        }

        searchHistoryRepository.removeAll();
        List<SearchData> allSearchData = searchHistoryRepository.getAllSearchData();

        assertTrue(allSearchData.isEmpty());
    }
}
