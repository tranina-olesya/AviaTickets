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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchFormDatePickerTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void searchFormDateFromTest() {
        ViewInteraction appCompatEditText = onView(withId(R.id.dateFrom));
        appCompatEditText.perform(longClick());

        ViewInteraction frameLayout = onView(withId(android.R.id.content));
        frameLayout.check(matches(isDisplayed()));
    }


    @Test
    public void searchFormDateToTest() {
        ViewInteraction appCompatEditText = onView(withId(R.id.dateTo));
        appCompatEditText.perform(longClick());

        ViewInteraction frameLayout = onView(withId(android.R.id.content));
        frameLayout.check(matches(isDisplayed()));
    }
}
