package ru.vsu.aviatickets.ui.searchform;


import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ui.helpers.DateTestHelper;
import ru.vsu.aviatickets.ui.main.MainActivity;
import ru.vsu.aviatickets.ui.utils.DateConvert;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNot.not;

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
    public void tooManySumPassengersTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("5"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("4"));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        adultsEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorOneTooManyPassengers))));
    }


    @Test
    public void noPassengersTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("0"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("0"));

        ViewInteraction infantsEditText = onView(withId(R.id.infantsCount));
        infantsEditText.perform(replaceText("0"));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        adultsEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorOneAdultRequired))));
    }

    @Test
    public void noDateFromTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("1"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("0"));

        ViewInteraction infantsEditText = onView(withId(R.id.infantsCount));
        infantsEditText.perform(replaceText("0"));

        ViewInteraction dateFromEditText = onView(withId(R.id.dateFrom));
        dateFromEditText.perform(replaceText(""));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        dateFromEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorDateOutbound))));
    }

    @Test
    public void dateFromWrongFormatTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("1"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("0"));

        ViewInteraction infantsEditText = onView(withId(R.id.infantsCount));
        infantsEditText.perform(replaceText("0"));

        ViewInteraction dateFromEditText = onView(withId(R.id.dateFrom));
        dateFromEditText.perform(replaceText(DateTestHelper.getDateStringWithDots(new Date())));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        dateFromEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorDateOutbound))));
    }

    @Test
    public void dateToDisabledTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("1"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("0"));

        ViewInteraction infantsEditText = onView(withId(R.id.infantsCount));
        infantsEditText.perform(replaceText("0"));

        ViewInteraction appCompatSpinner = onView(withId(R.id.routeType));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        ViewInteraction dateToEditText = onView(withId(R.id.dateTo));

        dateToEditText.check(matches(not(isEnabled())));
    }

    @Test
    public void noDateToTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("1"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("0"));

        ViewInteraction infantsEditText = onView(withId(R.id.infantsCount));
        infantsEditText.perform(replaceText("0"));

        ViewInteraction dateFromEditText = onView(withId(R.id.dateFrom));
        dateFromEditText.perform(replaceText(DateConvert.getDateWithSlashes(new Date())));

        ViewInteraction appCompatSpinner = onView(withId(R.id.routeType));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        ViewInteraction dateToEditText = onView(withId(R.id.dateTo));
        dateToEditText.perform(replaceText(""));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        dateToEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorDateInbound))));
    }

    @Test
    public void dateToWrongFormatTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("1"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("0"));

        ViewInteraction infantsEditText = onView(withId(R.id.infantsCount));
        infantsEditText.perform(replaceText("0"));

        ViewInteraction dateFromEditText = onView(withId(R.id.dateFrom));
        dateFromEditText.perform(replaceText(DateConvert.getDateWithSlashes(new Date())));

        ViewInteraction appCompatSpinner = onView(withId(R.id.routeType));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        ViewInteraction dateToEditText = onView(withId(R.id.dateTo));
        dateToEditText.perform(replaceText(DateTestHelper.getDateStringWithDots(new Date())));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        dateToEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorDateInbound))));
    }

    @Test
    public void yesterdayDateFromTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("1"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("0"));

        ViewInteraction infantsEditText = onView(withId(R.id.infantsCount));
        infantsEditText.perform(replaceText("0"));

        ViewInteraction dateFromEditText = onView(withId(R.id.dateFrom));
        dateFromEditText.perform(replaceText(DateConvert.getDateWithSlashes(DateTestHelper.getYesterdayDate())));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        dateFromEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorDateInPast))));
    }

    @Test
    public void dateToAfterDateFromTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("1"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("0"));

        ViewInteraction infantsEditText = onView(withId(R.id.infantsCount));
        infantsEditText.perform(replaceText("0"));

        ViewInteraction appCompatSpinner = onView(withId(R.id.routeType));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        Date date = new Date();
        ViewInteraction dateFromEditText = onView(withId(R.id.dateFrom));
        dateFromEditText.perform(replaceText(DateConvert.getDateWithSlashes(DateTestHelper.addDays(date, 4))));

        ViewInteraction dateToEditText = onView(withId(R.id.dateTo));
        dateToEditText.perform(replaceText(DateConvert.getDateWithSlashes(DateTestHelper.addDays(date, 2))));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        dateToEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorDateOutboundAfterDateIbound))));
    }

    @Test
    public void yesterdayDateToTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("1"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("0"));

        ViewInteraction infantsEditText = onView(withId(R.id.infantsCount));
        infantsEditText.perform(replaceText("0"));

        ViewInteraction appCompatSpinner = onView(withId(R.id.routeType));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        ViewInteraction dateFromEditText = onView(withId(R.id.dateFrom));
        dateFromEditText.perform(replaceText(DateConvert.getDateWithSlashes(new Date())));

        ViewInteraction dateToEditText = onView(withId(R.id.dateTo));
        dateToEditText.perform(replaceText(DateConvert.getDateWithSlashes(DateTestHelper.getYesterdayDate())));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        dateToEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorDateInPast))));
    }

    @Test
    public void noCityFromTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("1"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("0"));

        ViewInteraction infantsEditText = onView(withId(R.id.infantsCount));
        infantsEditText.perform(replaceText("0"));

        ViewInteraction appCompatSpinner = onView(withId(R.id.routeType));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        Date date = new Date();
        ViewInteraction dateFromEditText = onView(withId(R.id.dateFrom));
        dateFromEditText.perform(replaceText(DateConvert.getDateWithSlashes(date)));

        ViewInteraction dateToEditText = onView(withId(R.id.dateTo));
        dateToEditText.perform(replaceText(DateConvert.getDateWithSlashes(DateTestHelper.addDays(date, 1))));

        ViewInteraction cityFromEditText = onView(withId(R.id.cityFrom));
        cityFromEditText.perform(replaceText(""));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        cityFromEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorEmptyOrigin))));
    }

    @Test
    public void noCityToTest() {
        ViewInteraction adultsEditText = onView(withId(R.id.adultsCount));
        adultsEditText.perform(replaceText("1"));

        ViewInteraction childrenEditText = onView(withId(R.id.childrenCount));
        childrenEditText.perform(replaceText("0"));

        ViewInteraction infantsEditText = onView(withId(R.id.infantsCount));
        infantsEditText.perform(replaceText("0"));

        ViewInteraction appCompatSpinner = onView(withId(R.id.routeType));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        Date date = new Date();
        ViewInteraction dateFromEditText = onView(withId(R.id.dateFrom));
        dateFromEditText.perform(replaceText(DateConvert.getDateWithSlashes(date)));

        ViewInteraction dateToEditText = onView(withId(R.id.dateTo));
        dateToEditText.perform(replaceText(DateConvert.getDateWithSlashes(DateTestHelper.addDays(date, 1))));

        ViewInteraction cityFromEditText = onView(withId(R.id.cityFrom));
        cityFromEditText.perform(replaceText("a"));

        ViewInteraction cityToEditText = onView(withId(R.id.cityTo));
        cityToEditText.perform(replaceText(""));

        ViewInteraction appCompatButton = onView(withId(R.id.buttonSearch));
        appCompatButton.perform(click());

        cityToEditText.check(matches(hasErrorText(InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getString(R.string.errorEmptyDestination))));
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
