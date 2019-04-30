package ru.vsu.aviatickets.searchhistory;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public class SearchHistoryRepository {
    private static final String PREFS_NAME = "SEARCH_HISTORY_SHARED_PREF";
    private static final String SEARCH_DATA_KEY = "SEARCH_DATA";

    private Context context;

    public static SearchHistoryRepository getInstance(Context context) {
        return new SearchHistoryRepository(context);
    }

    private SearchHistoryRepository(Context context) {
        this.context = context;
        if (getAllSearchData() == null)
            putToSharedPreferences(new ArrayList<>());
    }

    private SharedPreferences getSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public void addSearchData(SearchData searchData) {
        List<SearchData> searchDataList = getAllSearchData();
        if (searchDataList == null)
            searchDataList = new ArrayList<>();
        searchDataList.add(searchData);
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
}
