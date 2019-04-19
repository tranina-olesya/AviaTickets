package ru.vsu.aviatickets.ui.ticketresults;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.providers.KiwiProviderAPI;
import ru.vsu.aviatickets.ticketssearch.providers.SkyScannerProviderAPI;
import ru.vsu.aviatickets.ticketssearch.providers.TicketProviderApi;
import ru.vsu.aviatickets.ui.main.MainActivity;

import static ru.vsu.aviatickets.ui.main.MainActivity.SEARCH_DATA;

public class TripActivity extends AppCompatActivity implements TripContractView {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;

    private TripPresenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    intent = new Intent(TripActivity.this, MainActivity.class);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        SearchData searchData = (SearchData) getIntent().getSerializableExtra(SEARCH_DATA);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        List<TicketProviderApi> providers = new ArrayList<>();
        providers.add(new KiwiProviderAPI());
        providers.add(new SkyScannerProviderAPI());
        TripModel tripModel = new TripModel(providers);

        presenter = new TripPresenter(tripModel);
        presenter.attachView(this);
        presenter.viewIsReady(searchData);
    }

    @Override
    public void showTrips(List<Trip> trips) {
        TripAdapter adapter = new TripAdapter(this, trips);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.progressDialogTripActivity));
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
