package ru.vsu.aviatickets.ui.ticketresults;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.providers.KiwiProviderAPI;
import ru.vsu.aviatickets.ticketssearch.providers.ProviderAPI;
import ru.vsu.aviatickets.ticketssearch.providers.SkyScannerProviderAPI;

public class TripActivity extends AppCompatActivity implements TripContractView {
    private TextView textView;
    private EditText editText;
    private Button buttonSubmit;
    private RecyclerView recyclerView;

    private TripPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        List<ProviderAPI> providers = new ArrayList<>();
        providers.add(new KiwiProviderAPI());
        providers.add(new SkyScannerProviderAPI());
        TripModel tripModel = new TripModel(providers);

        presenter = new TripPresenter(tripModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @Override
    public void showTrips(List<Trip> trips) {
        TripAdapter adapter = new TripAdapter(this, trips);
        recyclerView.setAdapter(adapter);
    }
}
