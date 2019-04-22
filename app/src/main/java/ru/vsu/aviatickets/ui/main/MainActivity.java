package ru.vsu.aviatickets.ui.main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ticketssearch.models.CabinClass;
import ru.vsu.aviatickets.ticketssearch.models.FlightType;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ui.ticketresults.TripActivity;

import static android.view.MotionEvent.ACTION_UP;

public class MainActivity extends AppCompatActivity implements MainContractView {

    public static final String SEARCH_DATA = "searchData";
    private final int DRAWABLE_RIGHT = 2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_bookmarks:

                    return true;
                case R.id.navigation_history:
                    return true;
            }
            return false;
        }
    };

    private MainPresenter presenter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSearch = (Button) findViewById(R.id.buttonSearch);
        editTextDateFrom = (EditText) findViewById(R.id.dateFrom);
        editTextDateTo = (EditText) findViewById(R.id.dateTo);
        editTextOrigin = (EditText) findViewById(R.id.cityFrom);
        editTextDestination = (EditText) findViewById(R.id.cityTo);
        editTextAdultsCount = (EditText) findViewById(R.id.adultsCount);
        editTextChildrenCount = (EditText) findViewById(R.id.childrenCount);
        editTextInfantsCount = (EditText) findViewById(R.id.infantsCount);
        checkboxTransfer = (CheckBox) findViewById(R.id.transfer);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fillRouteTypeSpinner();
        fillCabinClassSpinner();

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
                        presenter.calendarDateFrom();
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
                        presenter.calendarDateTo(editTextDateFrom.getText().toString());
                        return true;
                    }
                }
                return false;
            }
        });
        presenter = new MainPresenter();
        presenter.attachView(this);
    }

    private void fillRouteTypeSpinner() {
        String[] data = {getString(R.string.spinnerFlightTypeRound), getString(R.string.spinnerFlightTypeOneway)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        spinnerFlightType = (Spinner) findViewById(R.id.routeType);
        spinnerFlightType.setAdapter(adapter);
        spinnerFlightType.setPrompt(getString(R.string.hintRouteType));
    }

    private void fillCabinClassSpinner() {
        String[] data = {getString(R.string.spinnerCabinClassEconomy), getString(R.string.spinnerCabinClassBusiness)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        spinnerCabinClass = (Spinner) findViewById(R.id.cabinClass);
        spinnerCabinClass.setAdapter(adapter);
        spinnerCabinClass.setPrompt(getString(R.string.hintCabinClass));
    }

    @Override
    public SearchData getSearchData() {
        SearchData searchData = new SearchData();
        searchData.setOrigin(editTextOrigin.getText().toString());
        searchData.setDestination(editTextDestination.getText().toString());
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

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            searchData.setOutboundDate(simpleDateFormat.parse(editTextDateFrom.getText().toString()));
        } catch (ParseException e) {
            searchData.setOutboundDate(null);
            e.printStackTrace();
        }

        if (flightType == FlightType.ROUND) {
            try {
                searchData.setInboundDate(simpleDateFormat.parse(editTextDateTo.getText().toString()));
            } catch (ParseException e) {
                searchData.setInboundDate(null);
                e.printStackTrace();
            }
        }
        return searchData;
    }

    @Override
    public void showDateFromPickerDialog(int year, int month, int dayOfMonth) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
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
        Intent intent = new Intent(MainActivity.this, TripActivity.class);
        intent.putExtra(SEARCH_DATA, searchData);
        startActivity(intent);
    }

    @Override
    public void errorAdultCount(int resId) {
        editTextAdultsCount.requestFocus();
        editTextAdultsCount.setError(getString(resId), null);
    }

    @Override
    public void errorDateOutbound(int resId) {
        editTextDateFrom.requestFocus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = getDrawable(R.drawable.error_calendar_month_outline);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            editTextDateFrom.setError(getString(resId), drawable);
        } else
            editTextDateFrom.setError(getString(resId));
    }

    @Override
    public void errorDateInbound(int resId) {
        editTextDateTo.requestFocus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = getDrawable(R.drawable.error_calendar_month_outline);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            editTextDateTo.setError(getString(resId), drawable);
        } else
            editTextDateTo.setError(getString(resId));
    }
}
