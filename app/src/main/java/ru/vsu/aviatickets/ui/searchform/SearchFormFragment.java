package ru.vsu.aviatickets.ui.searchform;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.TooltipCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.api.entities.tripmodels.CabinClass;
import ru.vsu.aviatickets.api.entities.tripmodels.FlightType;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchPlace;
import ru.vsu.aviatickets.ui.main.MainActivity;
import ru.vsu.aviatickets.ui.tripresults.TripResultsFragment;
import ru.vsu.aviatickets.ui.utils.DateConvert;

import static android.view.MotionEvent.ACTION_UP;

public class SearchFormFragment extends Fragment implements SearchFormContractView {
    private final int DRAWABLE_RIGHT = 2;
    private SearchData searchData;
    private SearchFormPresenter presenter;

    private Spinner spinnerFlightType;
    private Spinner spinnerCabinClass;
    private EditText editTextDateFrom;
    private EditText editTextDateTo;
    private EditText editTextOrigin;
    private EditText editTextDestination;
    private EditText editTextAdultsCount;
    private EditText editTextChildrenCount;
    private EditText editTextInfantsCount;
    private CheckBox checkboxTransfer;
    private ImageButton changeCities;
    private Group progressGroup;

    public SearchFormFragment() {
    }

    public static SearchFormFragment getInstance(SearchData searchData) {
        SearchFormFragment searchFormFragment = new SearchFormFragment();
        searchFormFragment.searchData = searchData;
        return searchFormFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_form, container, false);

        Button buttonSearch = view.findViewById(R.id.buttonSearch);
        editTextDateFrom = view.findViewById(R.id.dateFrom);
        editTextDateTo = view.findViewById(R.id.dateTo);
        editTextOrigin = view.findViewById(R.id.cityFrom);
        editTextDestination = view.findViewById(R.id.cityTo);
        editTextAdultsCount = view.findViewById(R.id.adultsCount);
        editTextChildrenCount = view.findViewById(R.id.childrenCount);
        editTextInfantsCount = view.findViewById(R.id.infantsCount);
        checkboxTransfer = view.findViewById(R.id.transfer);
        spinnerFlightType = view.findViewById(R.id.routeType);
        spinnerCabinClass = view.findViewById(R.id.cabinClass);
        changeCities = view.findViewById(R.id.changeCities);
        progressGroup = view.findViewById(R.id.groupProgress);

        TooltipCompat.setTooltipText(editTextAdultsCount, getString(R.string.hintAdultsCount));
        TooltipCompat.setTooltipText(editTextChildrenCount, getString(R.string.hintChildrenCount));
        TooltipCompat.setTooltipText(editTextInfantsCount, getString(R.string.hintInfantsCount));

        fillRouteTypeSpinner();
        fillCabinClassSpinner();

        changeCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origin = editTextOrigin.getText().toString();
                String destination = editTextDestination.getText().toString();

                editTextOrigin.setText(destination);
                editTextDestination.setText(origin);
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.searchTickets();
            }
        });

        editTextDateFrom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == ACTION_UP) {
                    if (event.getRawX() >= (editTextDateFrom.getRight() - editTextDateFrom.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        presenter.calendarDateFrom(editTextDateFrom.getText().toString());
                        return true;
                    }
                }
                return false;
            }
        });

        editTextDateTo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == ACTION_UP) {
                    if (event.getRawX() >= (editTextDateTo.getRight() - editTextDateTo.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        String dateTo = editTextDateTo.getText().toString();
                        if (dateTo != null && !dateTo.isEmpty()) {
                            presenter.calendarDateTo(dateTo, false);
                        } else {
                            String date = editTextDateFrom.getText().toString();
                            presenter.calendarDateTo(date, true);
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        spinnerFlightType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                if (view != null)
                    presenter.flightTypeChanged(textView.getText().equals(getString(R.string.spinnerFlightTypeOneway)) ?
                            FlightType.ONEWAY : FlightType.ROUND);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        presenter = new SearchFormPresenter(new SearchFormModel());
        presenter.attachView(this);
        presenter.viewIsReady();
        return view;
    }

    private void fillRouteTypeSpinner() {
        String[] data = {getString(R.string.spinnerFlightTypeRound), getString(R.string.spinnerFlightTypeOneway)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, data);
        spinnerFlightType.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFlightType.setPrompt(getString(R.string.hintRouteType));
    }

    private void fillCabinClassSpinner() {
        String[] data = {getString(R.string.spinnerCabinClassEconomy), getString(R.string.spinnerCabinClassBusiness)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, data);
        spinnerCabinClass.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCabinClass.setPrompt(getString(R.string.hintCabinClass));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hideProgress();
    }

    @Override
    public SearchData getSearchData() {
        SearchData searchData = new SearchData();
        searchData.setOrigin(new SearchPlace(editTextOrigin.getText().toString(), null));
        searchData.setDestination(new SearchPlace(editTextDestination.getText().toString(), null));
        if (editTextAdultsCount.getText() != null && !editTextAdultsCount.getText().toString().isEmpty())
            searchData.setAdultsCount(Integer.parseInt(editTextAdultsCount.getText().toString()));
        if (editTextChildrenCount.getText() != null && !editTextChildrenCount.getText().toString().isEmpty())
            searchData.setChildrenCount(Integer.parseInt(editTextChildrenCount.getText().toString()));
        else
            searchData.setChildrenCount(0);
        if (editTextInfantsCount.getText() != null && !editTextInfantsCount.getText().toString().isEmpty())
            searchData.setInfantsCount(Integer.parseInt(editTextInfantsCount.getText().toString()));
        else
            searchData.setInfantsCount(0);
        searchData.setTransfers(checkboxTransfer.isChecked());

        CabinClass cabinClass = spinnerCabinClass.getSelectedItem().toString().equals(getString(R.string.spinnerCabinClassEconomy)) ?
                CabinClass.ECONOMY : CabinClass.BUSINESS;
        searchData.setCabinClass(cabinClass);

        FlightType flightType = spinnerFlightType.getSelectedItem().toString().equals(getString(R.string.spinnerFlightTypeRound)) ?
                FlightType.ROUND : FlightType.ONEWAY;
        searchData.setFlightType(flightType);

        searchData.setOutboundDate(DateConvert.getDateFromStringWithSlashes(editTextDateFrom.getText().toString()));

        if (flightType == FlightType.ROUND) {
            searchData.setInboundDate(DateConvert.getDateFromStringWithSlashes(editTextDateTo.getText().toString()));
        }
        return searchData;
    }

    public void setSearchData(SearchData searchData) {
        this.searchData = searchData;
    }

    @Override
    public void showDateFromPickerDialog(int year, int month, int dayOfMonth) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        presenter.dateFromSelected(year, month, day);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
        editTextDateFrom.setError(null);
    }

    @Override
    public void showDateToPickerDialog(int year, int month, int dayOfMonth) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        presenter.dateToSelected(year, month, day);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
        editTextDateTo.setError(null);
    }

    @Override
    public void setDateFromEditTextValue(String date) {
        editTextDateFrom.setText(date);
    }

    @Override
    public void setDateToEditTextValue(String date) {
        editTextDateTo.setText(date);
    }

    @Override
    public void showSearchResults(SearchData searchData) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            FragmentTransaction transaction = (FragmentTransaction) activity.getSupportFragmentManager().beginTransaction();
            ((MainActivity) getActivity()).hideSearchForm();
            transaction.add(R.id.fragmentContainer, TripResultsFragment.getInstance(searchData));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void errorAdultCount(int resId) {
        editTextAdultsCount.requestFocus();
        editTextAdultsCount.setError(getString(resId), null);
    }

    @Override
    public void errorInfantsCount(int resId) {
        editTextInfantsCount.requestFocus();
        editTextInfantsCount.setError(getString(resId), null);
    }

    @Override
    public void errorDateOutbound(int resId) {
        editTextDateFrom.requestFocus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = getContext().getDrawable(R.drawable.error_calendar_month_outline);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            editTextDateFrom.setError(getString(resId), drawable);
        } else
            editTextDateFrom.setError(getString(resId));
    }

    @Override
    public void errorDateInbound(int resId) {
        editTextDateTo.requestFocus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = getContext().getDrawable(R.drawable.error_calendar_month_outline);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            editTextDateTo.setError(getString(resId), drawable);
        } else
            editTextDateTo.setError(getString(resId));
    }

    @Override
    public void fillForm() {
        if (searchData == null)
            return;
        editTextOrigin.setText(searchData.getOrigin().getName());
        editTextDestination.setText(searchData.getDestination().getName());
        editTextAdultsCount.setText(String.valueOf(searchData.getAdultsCount()));
        editTextChildrenCount.setText(String.valueOf(searchData.getChildrenCount()));
        editTextInfantsCount.setText(String.valueOf(searchData.getInfantsCount()));
        spinnerCabinClass.setSelection(searchData.getCabinClass() == CabinClass.ECONOMY ? 0 : 1);
        spinnerFlightType.setSelection(searchData.getFlightType() == FlightType.ROUND ? 0 : 1);
        editTextDateFrom.setText(DateConvert.getDateWithSlashes(searchData.getOutboundDate()));
        editTextDateTo.setText(DateConvert.getDateWithSlashes(searchData.getInboundDate()));
        checkboxTransfer.setChecked(searchData.getTransfers());
    }

    @Override
    public boolean isSavingHistoryEnabled() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            return activity.getHistorySettings();
        }
        return false;
    }

    @Override
    public void hideKeyboard() {
        Activity activity = getActivity();
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void disableDateToInput() {
        editTextDateTo.setError(null);
        editTextDateTo.setEnabled(false);
    }

    @Override
    public void enableDateToInput() {
        editTextDateTo.setEnabled(true);
    }


    @Override
    public void cityNotFound() {
        Toast toast = Toast.makeText(getContext(), R.string.cityError, Toast.LENGTH_LONG);
        toast.setMargin(0, 0.1f);
        toast.show();
    }

    @Override
    public void showProgress() {
        progressGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if (progressGroup != null)
            progressGroup.setVisibility(View.GONE);
    }

    @Override
    public void noResponse() {
        Toast toast = Toast.makeText(getContext(), R.string.responseError, Toast.LENGTH_LONG);
        toast.setMargin(0, 0.1f);
        toast.show();
    }

    @Override
    public void errorOrigin(int resId) {
        editTextOrigin.requestFocus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = getContext().getDrawable(R.drawable.alert_circle_outline);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            editTextOrigin.setError(getString(resId), drawable);
        } else
            editTextOrigin.setError(getString(resId));
    }

    @Override
    public void errorDestination(int resId) {
        editTextDestination.requestFocus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = getContext().getDrawable(R.drawable.alert_circle_outline);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            editTextDestination.setError(getString(resId), drawable);
        } else
            editTextDestination.setError(getString(resId));
    }
}
