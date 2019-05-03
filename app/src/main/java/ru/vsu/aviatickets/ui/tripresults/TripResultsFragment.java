package ru.vsu.aviatickets.ui.tripresults;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.providers.KiwiProviderAPI;
import ru.vsu.aviatickets.ticketssearch.providers.SkyScannerProviderAPI;
import ru.vsu.aviatickets.ticketssearch.providers.TicketProviderApi;
import ru.vsu.aviatickets.ticketssearch.sort.SortFilterType;

public class TripResultsFragment extends Fragment implements TripResultsContractView {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private SearchData searchData;

    private TripResultsPresenter presenter;
    private TextView errorNoTicketsFound;
    private Spinner sortFilters;
    private ImageButton addBut;

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
        errorNoTicketsFound = view.findViewById(R.id.errorTicketsNotFound);
        sortFilters = view.findViewById(R.id.sortFilter);
        addBut = view.findViewById(R.id.addBookmark);

        fillSortFiltersSpinner();
        sortFilters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        presenter.filterChosen(SortFilterType.MIN_PRICE);
                        break;
                    case 2:
                        presenter.filterChosen(SortFilterType.MAX_PRICE);
                        break;
                    case 3:
                        presenter.filterChosen(SortFilterType.MIN_TIME);
                        break;
                    case 4:
                        presenter.filterChosen(SortFilterType.MAX_TIME);
                        break;
                    case 5:
                        presenter.filterChosen(SortFilterType.MIN_TRANSFERS);
                        break;
                    case 6:
                        presenter.filterChosen(SortFilterType.MAX_TRANSFERS);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addBookmark();
            }
        });
        List<TicketProviderApi> providers = new ArrayList<>();
        providers.add(new KiwiProviderAPI());
        providers.add(new SkyScannerProviderAPI());
        TripResultsModel tripResultsModel = new TripResultsModel(providers);
        BookmarkAdditionModel bookmarkAdditionModel = new BookmarkAdditionModel();

        presenter = new TripResultsPresenter(tripResultsModel, bookmarkAdditionModel);
        presenter.attachView(this);
        presenter.viewIsReady(searchData);
        return view;
    }

    @Override
    public void showTrips(List<Trip> trips) {
        errorNoTicketsFound.setVisibility(View.GONE);
        TripResultsAdapter adapter = new TripResultsAdapter(getContext(), trips);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public BookmarkRoute addBookmarkRouteData() {
        BookmarkRoute bookmarkRoute = new BookmarkRoute(searchData.getOrigin(),searchData.getDestination(),searchData.getAdultsCount(),
                                            searchData.getChildrenCount(),searchData.getInfantsCount(),searchData.getCabinClass().toString(),
                                            searchData.getTransfers());

        return bookmarkRoute;
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
        Toast toast = Toast.makeText(getContext(), R.string.cityError, Toast.LENGTH_LONG);
        toast.setMargin(0, 0.1f);
        FragmentActivity activity = getActivity();
        if (activity != null)
            activity.onBackPressed();
        toast.show();
    }

    @Override
    public void ticketsNotFound() {
        errorNoTicketsFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void noResponse() {
        Toast toast = Toast.makeText(getContext(), R.string.responseError, Toast.LENGTH_LONG);
        toast.setMargin(0, 0.1f);
        FragmentActivity activity = getActivity();
        if (activity != null)
            activity.onBackPressed();
        toast.show();
    }

    private void fillSortFiltersSpinner() {
        String[] data = {getString(R.string.hintSortFilters), getString(R.string.spinnerSortFiltersMinPrice), getString(R.string.spinnerSortFiltersMaxPrice),
                getString(R.string.spinnerSortFiltersMinTime), getString(R.string.spinnerSortFiltersMaxTime),
                getString(R.string.spinnerSortFiltersMinTransfers), getString(R.string.spinnerSortFiltersMaxTransfers)};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = null;
                if (position == 0) {
                    TextView textView = new TextView(getContext());
                    textView.setVisibility(View.GONE);
                    view = textView;
                } else {
                    view = super.getDropDownView(position, null, parent);
                }
                return view;
            }

        };
        sortFilters.setAdapter(adapter);
        sortFilters.setPrompt(getString(R.string.hintSortFilters));
    }
}
