package ru.vsu.aviatickets.searchhistory;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public class SearchHistoryRepository {
    private static SearchHistoryRepository instance;
    private static final String PREFS_NAME_DEFAULT = "SEARCH_HISTORY_SHARED_PREF";
    private static final String SEARCH_DATA_KEY = "SEARCH_DATA";

    private static String prefsName = PREFS_NAME_DEFAULT;

    public static SearchHistoryRepository getInstance() {
        return getInstance(PREFS_NAME_DEFAULT);
    }

    public static SearchHistoryRepository getInstance(String name) {
        prefsName = name;
        if (instance == null)
            instance = new SearchHistoryRepository();
        return instance;
    }

    private SearchHistoryRepository() {
        if (getAllSearchData() == null)
            putToSharedPreferences(new ArrayList<>());
    }

    private static SharedPreferences getSharedPreferences() {
        SharedPreferences sharedPreferences = App.getInstance().getBaseContext().getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public void addSearchData(SearchData searchData) {
        List<SearchData> searchDataList = getAllSearchData();
        if (searchDataList == null)
            searchDataList = new ArrayList<>();
        searchDataList.add(0, searchData);
        putToSharedPreferences(searchDataList);
    }

    public void removeSearchData(int index) {
        List<SearchData> searchDataList = getAllSearchData();
        searchDataList.remove(index);
        putToSharedPreferences(searchDataList);
    }

    public List<SearchData> getAllSearchData() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (!sharedPreferences.contains(SEARCH_DATA_KEY))
            return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<SearchData>>() {
        }.getType();
        String json = sharedPreferences.getString(SEARCH_DATA_KEY, "");

        return gson.fromJson(json, type);
    }

    private void putToSharedPreferences(List<SearchData> searchDataList) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(SEARCH_DATA_KEY, gson.toJson(searchDataList));
        editor.apply();
    }

    public void removeAll() {
        putToSharedPreferences(new ArrayList<>());
    }
}
