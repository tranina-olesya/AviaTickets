package ru.vsu.aviatickets.ui.searchform;


import android.support.test.InstrumentationRegistry;
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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchFormErrorMessagesTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void tooManyPassengersTest() {
        ViewInteraction appCompatEditText = onView(withId(R.id.adultsCount));
        appCompatEditText.perform(replaceText("9"));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        appCompatEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorOneTooManyPassengers))));
    }


    @Test
    public void tooManyInfantsTest() {
        ViewInteraction appCompatEditText = onView(withId(R.id.adultsCount));
        appCompatEditText.perform(replaceText("2"));

        ViewInteraction appCompatEditText2 = onView(withId(R.id.infantsCount));
        appCompatEditText2.perform(replaceText("3"));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        appCompatEditText2.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorInfantsMoreThanAdults))));
    }
}
