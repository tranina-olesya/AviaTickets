package ru.vsu.aviatickets.ui.searchform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.api.entities.SearchHistoryEntry;
import ru.vsu.aviatickets.api.entities.tripmodels.FlightType;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.providers.APIError;
import ru.vsu.aviatickets.api.providers.TripAPIProvider;

public class SearchFormPresenter {
    private SearchFormContractView view;
    private SearchFormModel model;

    public SearchFormPresenter(SearchFormModel model) {
        this.model = model;
    }

    public void attachView(SearchFormContractView view) {
        this.view = view;
    }

    public void viewIsReady() {
        view.fillForm();
    }

    public void calendarDateFrom(String date) {
        Calendar parseDateFrom = parseDateFrom(date);
        Calendar calendar = parseDateFrom != null ? parseDateFrom : Calendar.getInstance();
        view.showDateFromPickerDialog(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void calendarDateTo(String date, boolean addDays) {
        Calendar parseDateFrom = parseDateFrom(date);
        Calendar calendar = parseDateFrom != null ? parseDateFrom : Calendar.getInstance();
        if (addDays)
            calendar.add(Calendar.DAY_OF_MONTH, 1);
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
        view.hideKeyboard();
        SearchData searchData = view.getSearchData();
        if (checkSearchData(searchData)) {
            view.showProgress();
            model.searchCities(searchData, new TripAPIProvider.CityCallback() {
                @Override
                public void onComplete(String originCode, String destinationCode) {
                    view.hideProgress();
                    searchData.getOrigin().setCode(originCode);
                    searchData.getDestination().setCode(destinationCode);
                    if (view.isSavingHistoryEnabled())
                        model.addSearchData(new SearchHistoryEntry(App.getUserCode(), searchData), null);
                    view.showSearchResults(searchData);
                }

                @Override
                public void onFail(APIError error) {
                    switch (error) {
                        case NO_RESPONSE:
                            view.noResponse();
                            break;
                        case CITY_NOT_FOUND:
                            view.cityNotFound();
                            break;
                    }
                    view.hideProgress();
                }
            });
        }
    }

    private boolean checkSearchData(SearchData searchData) {
        if (searchData.getAdultsCount() == null || searchData.getAdultsCount() < 1) {
            view.errorAdultCount(R.string.errorOneAdultRequired);
            return false;
        } else if (searchData.getAdultsCount() + searchData.getChildrenCount() + searchData.getInfantsCount() > 8) {
            view.errorAdultCount(R.string.errorOneTooManyPassengers);
            return false;
        } else if (searchData.getAdultsCount() < searchData.getInfantsCount()) {
            view.errorInfantsCount(R.string.errorInfantsMoreThanAdults);
            return false;
        } else if (searchData.getOutboundDate() == null) {
            view.errorDateOutbound(R.string.errorDateOutbound);
            return false;
        } else if (searchData.getOutboundDate().before(getYesterday())) {
            view.errorDateOutbound(R.string.errorDateInPast);
            return false;
        } else if (searchData.getFlightType() == FlightType.ROUND) {
            if (searchData.getInboundDate() == null) {
                view.errorDateInbound(R.string.errorDateInbound);
                return false;
            } else if (searchData.getInboundDate().before(getYesterday())) {
                view.errorDateInbound(R.string.errorDateInPast);
                return false;
            } else if (!searchData.getOutboundDate().before(searchData.getInboundDate())) {
                view.errorDateInbound(R.string.errorDateOutboundAfterDateIbound);
                return false;
            }
        }
        return true;
    }

    private Date getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    private Calendar parseDateFrom(String strDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = sdf.parse(strDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void flightTypeChanged(FlightType flightType) {
        if (flightType == FlightType.ONEWAY)
            view.disableDateToInput();
        else
            view.enableDateToInput();
    }
}
