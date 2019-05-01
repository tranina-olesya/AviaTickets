package ru.vsu.aviatickets.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ui.bookmarks.BookmarksRouteFragment;
import ru.vsu.aviatickets.ui.searchform.SearchFormFragment;
import ru.vsu.aviatickets.ui.searchhistory.SearchHistoryFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private BottomNavigationView navigation;

    private SearchFormFragment searchFormFragment = new SearchFormFragment();
    private BookmarksRouteFragment bookmarksRouteFragment = new BookmarksRouteFragment();
    private SearchHistoryFragment searchHistoryFragment = new SearchHistoryFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    fragmentTransaction.replace(R.id.fragmentContainer, searchFormFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_bookmarks:
                    fragmentTransaction.replace(R.id.fragmentContainer, bookmarksRouteFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_history:
                    fragmentTransaction.replace(R.id.fragmentContainer, searchHistoryFragment);
                    fragmentTransaction.commit();
                    return true;
                default:
                    fragmentTransaction.commit();
            }
            return false;
        }
    };

    private FragmentManager.OnBackStackChangedListener onBackStackChangedListener
            = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null)
                supportActionBar.setDisplayHomeAsUpEnabled(fragmentManager.getBackStackEntryCount() > 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(onBackStackChangedListener);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_search);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        fragmentManager.popBackStack();
        return super.onOptionsItemSelected(item);
    }

    public void setSearchFormFragmentWithSearchData(SearchData searchData) {
        navigation.setSelectedItemId(R.id.navigation_search);
        searchFormFragment = SearchFormFragment.getInstance(searchData);
        navigation.setSelectedItemId(R.id.navigation_search);
    }
}
