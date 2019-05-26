package ru.vsu.aviatickets.ui.fulltrip;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.api.entities.tripmodels.Trip;

public class FullTripActivity extends AppCompatActivity {
    public static final String TRIP_EXTRA = "trip";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_trip);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        Trip trip = (Trip) getIntent().getSerializableExtra(TRIP_EXTRA);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle("");
        FullTripAdapter adapter = new FullTripAdapter(this, trip);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
