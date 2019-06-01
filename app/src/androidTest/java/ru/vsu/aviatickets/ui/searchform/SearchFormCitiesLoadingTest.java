package ru.vsu.aviatickets.ui.searchform;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchFormCitiesLoadingTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private String dateFrom;
    private String dateTo;

    @Before
    public void initDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        dateFrom = String.format("%d/%d/%d", c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
        c.add(Calendar.DAY_OF_MONTH, 1);
        dateTo = String.format("%d/%d/%d", c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
    }

    @Test
    public void searchFormCitiesLoadingTest() {
        ViewInteraction appCompatEditText = onView(withId(R.id.cityFrom));
        appCompatEditText.perform(replaceText("Лондон"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(withId(R.id.cityTo));
        appCompatEditText2.perform(replaceText("Москва"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(withId(R.id.dateFrom));
        appCompatEditText3.perform(replaceText(dateFrom), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(withId(R.id.dateTo));
        appCompatEditText4.perform(replaceText(dateTo), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        ViewInteraction progressBar = onView(withId(R.id.progressCityFrom));
        progressBar.check(matches(isDisplayed()));

        ViewInteraction progressBar2 = onView(withId(R.id.progressCityTo));
        progressBar2.check(matches(isDisplayed()));
    }
}
