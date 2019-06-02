package ru.vsu.aviatickets.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.ui.bookmarks.BookmarksRouteFragment;
import ru.vsu.aviatickets.ui.searchform.SearchFormFragment;
import ru.vsu.aviatickets.ui.searchhistory.SearchHistoryFragment;
import ru.vsu.aviatickets.ui.signin.SignInActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "SETTINGS_SHARED_PREF";
    private static final String SEARCH_HISTORY_KEY = "SEARCH_HISTORY";
    private SharedPreferences settingsSharedPreferences;

    private FragmentManager fragmentManager;

    private BottomNavigationView navigation;

    private GoogleSignInClient googleSignInClient;

    private SearchFormFragment searchFormFragment = new SearchFormFragment();
    private BookmarksRouteFragment bookmarksRouteFragment = new BookmarksRouteFragment();
    private SearchHistoryFragment searchHistoryFragment = new SearchHistoryFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setSubtitle("");
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    fragmentTransaction
                            .replace(R.id.fragmentContainer, searchFormFragment)
                            .show(searchFormFragment);
                    fragmentTransaction.commit();
                    if (actionBar != null)
                        actionBar.setTitle(R.string.title_search);
                    return true;
                case R.id.navigation_bookmarks:
                    fragmentTransaction.replace(R.id.fragmentContainer, bookmarksRouteFragment);
                    fragmentTransaction.commit();
                    if (actionBar != null)
                        actionBar.setTitle(R.string.title_bookmarks);
                    return true;
                case R.id.navigation_history:
                    fragmentTransaction.replace(R.id.fragmentContainer, searchHistoryFragment);
                    fragmentTransaction.commit();
                    if (actionBar != null)
                        actionBar.setTitle(R.string.title_history);
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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(onBackStackChangedListener);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_search);

        settingsSharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (getHistorySettings() == null)
            saveHistorySettings(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.searchHistorySettings:
                boolean checked = item.isChecked();
                saveHistorySettings(!checked);
                item.setChecked(!checked);
                return true;
            case R.id.signInSettings:
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                return true;
            case R.id.signOutSettings:
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signOut() {
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast toast = Toast.makeText(MainActivity.this, R.string.successfullySignedOut, Toast.LENGTH_SHORT);
                        toast.setMargin(0, 0.1f);
                        toast.show();
                        invalidateOptionsMenu();
                        startActivity(new Intent(MainActivity.this, SignInActivity.class));
                    }
                });
    }

    public void setSearchFormFragmentWithSearchData(SearchData searchData) {
        navigation.setSelectedItemId(R.id.navigation_search);
        searchFormFragment = SearchFormFragment.getInstance(searchData);
        navigation.setSelectedItemId(R.id.navigation_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (App.getUserCode() == null) {
            menu.findItem(R.id.signInSettings).setVisible(true);
            menu.findItem(R.id.signOutSettings).setVisible(false);
            menu.findItem(R.id.searchHistorySettings).setEnabled(false);
        } else {
            menu.findItem(R.id.signInSettings).setVisible(false);
            menu.findItem(R.id.signOutSettings).setVisible(true);
            menu.findItem(R.id.searchHistorySettings).setEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Boolean historySettings = getHistorySettings();
        if (historySettings != null)
            menu.findItem(R.id.searchHistorySettings).setChecked(historySettings);
        return super.onPrepareOptionsMenu(menu);
    }

    private void saveHistorySettings(boolean value) {
        SharedPreferences.Editor editor = settingsSharedPreferences.edit();
        editor.putBoolean(SEARCH_HISTORY_KEY, value);
        editor.apply();
    }

    public Boolean getHistorySettings() {
        if (settingsSharedPreferences.contains(SEARCH_HISTORY_KEY))
            return settingsSharedPreferences.getBoolean(SEARCH_HISTORY_KEY, false);
        return null;
    }

    public void hideSearchForm() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(searchFormFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            navigation.setSelectedItemId(R.id.navigation_search);
        } else
            super.onBackPressed();
    }
}
