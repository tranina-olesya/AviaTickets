package ru.vsu.aviatickets.ui.searchform;

import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;

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

    void errorInfantsCount(int resId);

    void fillForm();

    boolean isSavingHistoryEnabled();

    void hideKeyboard();

    void disableDateToInput();

    void enableDateToInput();

    void cityNotFound();

    void showProgress();

    void hideProgress();

    void noResponse();

    void errorDestination(int resId);

    void errorOrigin(int resId);
}
