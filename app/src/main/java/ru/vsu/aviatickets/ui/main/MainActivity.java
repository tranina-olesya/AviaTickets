package ru.vsu.aviatickets.ui.main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity implements MainContractView {
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
        Button buttonPickDateFrom = (Button) findViewById(R.id.buttonPickDateFrom);
        Button buttonPickDateTo = (Button) findViewById(R.id.buttonPickDateTo);
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

        buttonPickDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.calendarDateFrom();
            }
        });

        buttonPickDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.calendarDateTo();
            }
        });

        presenter = new MainPresenter();
        presenter.attachView(this);
    }

    private void fillRouteTypeSpinner() {
        String[] data = {getString(R.string.spinnerFlightTypeOneway), getString(R.string.spinnerFlightTypeRound)};
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
        searchData.setAdultsCount(Integer.parseInt(editTextAdultsCount.getText().toString()));
        searchData.setChildrenCount(Integer.parseInt(editTextChildrenCount.getText().toString()));
        searchData.setInfantsCount(Integer.parseInt(editTextInfantsCount.getText().toString()));
        searchData.setTransfers(checkboxTransfer.isChecked());

        CabinClass cabinClass = spinnerCabinClass.getSelectedItem().toString().equals(getString(R.string.spinnerCabinClassEconomy)) ?
                CabinClass.ECONOMY : CabinClass.BUSINESS;
        searchData.setCabinClass(cabinClass);

        FlightType flightType = spinnerFlightType.getSelectedItem().toString().equals(getString(R.string.spinnerFlightTypeRound)) ?
                FlightType.ROUND : FlightType.ONEWAY;
        searchData.setFlightType(flightType);

        try {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            searchData.setOutboundDate(simpleDateFormat.parse(editTextDateFrom.getText().toString()));
            if (flightType == FlightType.ROUND)
                searchData.setInboundDate(simpleDateFormat.parse(editTextDateTo.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
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
        intent.putExtra("searchData", searchData);
        startActivity(intent);
    }
}
