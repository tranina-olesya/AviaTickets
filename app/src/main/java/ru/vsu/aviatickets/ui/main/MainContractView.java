package ru.vsu.aviatickets.ui.main;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public interface MainContractView {
    SearchData getSearchData();

    void showDateFromPickerDialog(int year, int month, int dayOfMonth);

    void showDateToPickerDialog(int year, int month, int dayOfMonth);

    void setDateFromEditTextValue(String date);

    void setDateToEditTextValue(String date);

    void showSearchResults(SearchData searchData);
}
