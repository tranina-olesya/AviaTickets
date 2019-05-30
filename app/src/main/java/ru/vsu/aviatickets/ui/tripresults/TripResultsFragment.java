package ru.vsu.aviatickets.ui.tripresults;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.tripmodels.FlightType;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.entities.tripmodels.Trip;
import ru.vsu.aviatickets.ticketssearch.sort.SortFilterType;
import ru.vsu.aviatickets.ui.utils.DateConvert;

public class TripResultsFragment extends Fragment implements TripResultsContractView {

    private RecyclerView recyclerView;
    private TripResultsAdapter adapter;
    private SearchData searchData;
    private AnimationDrawable animationDrawable;
    private ImageView progressBar;

    private TripResultsPresenter presenter;
    private TextView errorNoTicketsFound;
    private Spinner sortFilters;
    private ImageButton addButton;

    private Group groupTripResults;
    private Group groupProgress;

    private List<Trip> loadedTrips;
    private List<Trip> shownTrips;

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
        addButton = view.findViewById(R.id.addBookmark);
        progressBar = view.findViewById(R.id.progress);

        groupTripResults = view.findViewById(R.id.groupResults);
        groupProgress = view.findViewById(R.id.groupProgress);

        fillSortFiltersSpinner();
        initializeProgressBar();

        sortFilters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                textView.setCompoundDrawablePadding(20);
                switch (position) {
                    case 1:
                        presenter.filterChosen(SortFilterType.MIN_PRICE);
                        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.sort_ascending, 0, 0, 0);
                        break;
                    case 2:
                        presenter.filterChosen(SortFilterType.MAX_PRICE);
                        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.sort_descending, 0, 0, 0);
                        break;
                    case 3:
                        presenter.filterChosen(SortFilterType.MIN_TIME);
                        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.sort_ascending, 0, 0, 0);
                        break;
                    case 4:
                        presenter.filterChosen(SortFilterType.MAX_TIME);
                        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.sort_descending, 0, 0, 0);
                        break;
                    case 5:
                        presenter.filterChosen(SortFilterType.MIN_TRANSFERS);
                        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.sort_ascending, 0, 0, 0);
                        break;
                    case 6:
                        presenter.filterChosen(SortFilterType.MAX_TRANSFERS);
                        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.sort_descending, 0, 0, 0);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.bookmarkButtonClicked();
            }
        });

        loadedTrips = new ArrayList<>();
        shownTrips = new ArrayList<>();

        TripResultsModel tripResultsModel = new TripResultsModel();
        BookmarkAdditionModel bookmarkAdditionModel = new BookmarkAdditionModel();

        presenter = new TripResultsPresenter(tripResultsModel, bookmarkAdditionModel);
        presenter.attachView(this);
        presenter.viewIsReady(searchData);

        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            String route = String.format("%s - %s", searchData.getOrigin().getName(), searchData.getDestination().getName());
            actionBar.setTitle(route);
            String date = searchData.getFlightType() == FlightType.ONEWAY ?
                    DateConvert.getDayMonthString(searchData.getOutboundDate()) :
                    DateConvert.getDayMonthString(searchData.getOutboundDate(), searchData.getInboundDate());
            actionBar.setSubtitle(date);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == shownTrips.size() - 5) {
                    loadMore();
                }
            }
        });
        return view;
    }

    @Override
    public void showTrips() {
        errorNoTicketsFound.setVisibility(View.GONE);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            shownTrips = new ArrayList<>();
            adapter = new TripResultsAdapter(getContext(), shownTrips);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public BookmarkRoute addBookmarkRouteData() {
        BookmarkRoute bookmarkRoute = new BookmarkRoute(searchData.getOrigin().getName(), searchData.getDestination().getName(), searchData.getAdultsCount(),
                searchData.getChildrenCount(), searchData.getInfantsCount(), searchData.getFlightType(),
                searchData.getTransfers(), searchData.getCabinClass());

        return bookmarkRoute;
    }

    @Override
    public void showProgress() {
        groupProgress.setVisibility(View.VISIBLE);
        animationDrawable.start();
    }

    @Override
    public void hideProgress() {
        animationDrawable.stop();
        groupProgress.setVisibility(View.GONE);
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

    @Override
    public void hideGroupTripResults() {
        groupTripResults.setVisibility(View.GONE);
    }

    @Override
    public void showGroupTripResults() {
        groupTripResults.setVisibility(View.VISIBLE);
    }

    @Override
    public void bookmarkAdded() {
        addButton.setImageDrawable(getResources().getDrawable(R.drawable.heart));
    }

    @Override
    public void bookmarkDeleted() {
        addButton.setImageDrawable(getResources().getDrawable(R.drawable.heart_outline));
    }

    private void fillSortFiltersSpinner() {
        String[] data = {getString(R.string.hintSortFilters), getString(R.string.spinnerSortFiltersMinPrice), getString(R.string.spinnerSortFiltersMaxPrice),
                getString(R.string.spinnerSortFiltersMinTime), getString(R.string.spinnerSortFiltersMaxTime),
                getString(R.string.spinnerSortFiltersMinTransfers), getString(R.string.spinnerSortFiltersMaxTransfers)};
        int[] imageResources = {0, R.drawable.sort_ascending, R.drawable.sort_descending,
                R.drawable.sort_ascending, R.drawable.sort_descending,
                R.drawable.sort_ascending, R.drawable.sort_descending};
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
                    TextView textView = (TextView) view;
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(imageResources[position], 0, 0, 0);
                    textView.setCompoundDrawablePadding(20);
                }
                return view;
            }

        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortFilters.setAdapter(adapter);
        sortFilters.setPrompt(getString(R.string.hintSortFilters));
    }

    private void initializeProgressBar() {
        progressBar.setBackgroundResource(R.drawable.loading);
        animationDrawable = (AnimationDrawable) progressBar.getBackground();
        Bitmap b = ((BitmapDrawable) getActivity().getDrawable(R.drawable.frame_0)).getBitmap();

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        int width = b.getWidth();
        int height = b.getHeight();
        int progressWidth = (int) (screenWidth * 0.5);
        int progressHeight = (progressWidth * height / width);
        progressBar.getLayoutParams().height = progressHeight;
        progressBar.getLayoutParams().width = progressWidth;
        progressBar.setY(screenHeight * 0.45f);
    }

    @Override
    public SearchData getSearchData() {
        return searchData;
    }

    @Override
    public void disableBookmarksButton() {
        addButton.setEnabled(false);
    }

    @Override
    public void enableBookmarksButton() {
        addButton.setEnabled(true);
    }

    @Override
    public void setLoadedTrips(List<Trip> loadedTrips) {
        this.loadedTrips = loadedTrips;
    }

    @Override
    public void loadMore() {
        FragmentActivity activity = getActivity();
        if (activity != null && shownTrips != null && loadedTrips != null) {
            int shownTripsSize = shownTrips.size();
            int loadedTripsSize = loadedTrips.size();
            if (loadedTripsSize > 0) {
                int addItemsCount = Math.min(loadedTripsSize - shownTripsSize, 10);
                shownTrips.addAll(loadedTrips.subList(shownTripsSize, shownTripsSize + addItemsCount));
                adapter.notifyItemRangeChanged(shownTripsSize, addItemsCount);
            }
        }
    }

    @Override
    public void showNoResponseToast() {
        if (getActivity() != null) {
            Toast toast = Toast.makeText(getContext(), R.string.responseError, Toast.LENGTH_LONG);
            toast.setMargin(0, 0.1f);
            toast.show();
        }
    }
}
