package ru.vsu.aviatickets.ui.main;

import java.util.Calendar;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public class MainPresenter {
    private MainContractView view;

    public MainPresenter() {
    }

    public void attachView(MainContractView view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    public void calendarDateFrom() {
        Calendar calendar = Calendar.getInstance();
        view.showDateFromPickerDialog(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void calendarDateTo() {
        Calendar calendar = Calendar.getInstance();
        view.showDateToPickerDialog(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void dateFromSelected(int year, int month, int day) {
        view.setDateFromEditTextValue(day + "/" + (month + 1) + "/" + year);
    }

    public void dateToSelected(int year, int month, int day) {
        view.setDateToEditTextValue(day + "/" + (month + 1) + "/" + year);
    }

    public void searchTickets() {
        SearchData searchData = view.getSearchData();
        view.showSearchResults(searchData);
    }
}
