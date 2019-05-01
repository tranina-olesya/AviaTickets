package ru.vsu.aviatickets.ui.searchform;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public interface SearchFormContractView {
    SearchData getSearchData();

    void showDateFromPickerDialog(int year, int month, int dayOfMonth);

    void showDateToPickerDialog(int year, int month, int dayOfMonth);

    void setDateFromEditTextValue(String date);

    void setDateToEditTextValue(String date);

    void showSearchResults(SearchData searchData);

    void errorAdultCount(int resId);

    void errorDateOutbound(int resId);

    void errorDateInbound(int resId);

    void fillForm();
}
