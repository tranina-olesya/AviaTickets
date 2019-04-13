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

import java.util.Calendar;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ui.ticketresults.TripActivity;

public class MainActivity extends AppCompatActivity {
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
    private Button buttonTips;
    private Spinner spinner;
    private Button buttonPickDateFrom;
    private Button buttonPickDateTo;
    private EditText dateFrom;
    private EditText dateTo;
    private EditText origin;
    private EditText destination;
    private EditText adultsCount;
    private EditText childrenCount;
    private EditText infantsCount;
    private CheckBox transfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonTips = (Button) findViewById(R.id.buttonTips);
        buttonPickDateFrom = (Button) findViewById(R.id.buttonPickDateFrom);
        buttonPickDateTo = (Button) findViewById(R.id.buttonPickDateTo);
        dateFrom = (EditText) findViewById(R.id.dateFrom);
        dateTo = (EditText) findViewById(R.id.dateTo);
        origin = (EditText) findViewById(R.id.cityFrom);
        destination = (EditText) findViewById(R.id.cityTo);
        adultsCount = (EditText) findViewById(R.id.adultsCount);
        childrenCount = (EditText) findViewById(R.id.childrenCount);
        infantsCount = (EditText) findViewById(R.id.infantsCount);
        transfer = (CheckBox) findViewById(R.id.transfer);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fillRouteTypeSpinner();
        Intent intent = new Intent(MainActivity.this, TripActivity.class);
        buttonTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        buttonPickDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateFrom.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        buttonPickDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateTo.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });


    }

    private String[] data = {"В одну сторону", "Туда-обратно"};

    private void fillRouteTypeSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        spinner = (Spinner) findViewById(R.id.routeType);
        spinner.setAdapter(adapter);
        spinner.setPrompt(getString(R.string.hintRouteType));
    }

}
