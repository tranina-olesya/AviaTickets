package ru.vsu.aviatickets.ui.tripresults;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.providers.KiwiProviderAPI;
import ru.vsu.aviatickets.ticketssearch.providers.SkyScannerProviderAPI;
import ru.vsu.aviatickets.ticketssearch.providers.TicketProviderApi;

public class TripResultsFragment extends Fragment implements TripResultsContractView {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private SearchData searchData;

    private TripResultsPresenter presenter;

    public TripResultsFragment() {
    }

    public static TripResultsFragment getInstance(SearchData searchData) {
        TripResultsFragment tripResultsResultsFragment = new TripResultsFragment();
        tripResultsResultsFragment.searchData = searchData;
        return tripResultsResultsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip, container, false);
        recyclerView = view.findViewById(R.id.RecyclerView);
        List<TicketProviderApi> providers = new ArrayList<>();
        providers.add(new KiwiProviderAPI());
        providers.add(new SkyScannerProviderAPI());
        TripResultsModel tripResultsModel = new TripResultsModel(providers);

        presenter = new TripResultsPresenter(tripResultsModel);
        presenter.attachView(this);
        presenter.viewIsReady(searchData);
        return view;
    }

    @Override
    public void showTrips(List<Trip> trips) {
        TripResultsAdapter adapter = new TripResultsAdapter(getContext(), trips);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(getContext(), "", getString(R.string.progressDialogTripActivity));
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void cityNotFound() {
        Toast toast = Toast.makeText(getContext(), "Город не найден", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void ticketsNotFound() {
        Toast toast = Toast.makeText(getContext(), "Билеты не найдены", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void noResponse() {
        Toast toast = Toast.makeText(getContext(), "Нет ответа", Toast.LENGTH_SHORT);
        toast.show();
    }
}
