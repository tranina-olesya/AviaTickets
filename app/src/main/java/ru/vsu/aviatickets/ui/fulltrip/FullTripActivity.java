package ru.vsu.aviatickets.ui.fulltrip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ticketssearch.models.Trip;

public class FullTripActivity extends AppCompatActivity {
    public static final String TRIP_EXTRA = "trip";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_trip);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        FullTripAdapter adapter = new FullTripAdapter(this, (Trip) getIntent().getSerializableExtra(TRIP_EXTRA));
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
