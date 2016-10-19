package com.bignerdranch.android.criminalintent;
import android.app.Application;
import android.test.ApplicationTestCase;
/**
 * Created by meghanhogan on 10/17/16.
 */
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
@RunWith(AndroidJUnit4.class)
public class ApplicationTest {
    @Rule
    public ActivityTestRule<CrimeListActivity> mActivityRule = new
            ActivityTestRule<>(CrimeListActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
    }

    @Test
    public void testChooseCrime() {
        // Test ability to choose crime fragment from the recycler list
        // Click on the RecyclerView item at position crimeToSelect
        for (int crimeID = 0; crimeID < 2; crimeID++) {

        onView(withId(R.id.crime_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.crime_title)).check(matches(withText("Crime #0")));
        Espresso.pressBack();
          }


    }

    @Test
    public void testSetSolved() {
        // Test the solved checkbox is communicated to list and retained in item view
        // set all to false
        for (Crime crime : CrimeLab.get(InstrumentationRegistry.getContext()).getCrimes()) {
            crime.setSolved(false);
        }
        // Test ability to set crime to solved and xfer back to CrimeListFragment
        // set some to true
        for (int crimeID = 0; crimeID < 1; crimeID++) {

            onView(withId(R.id.crime_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(crimeID, click()));
            onView(withId(R.id.crime_solved)).check(matches(isNotChecked()));
            onView(withId(R.id.crime_solved)).perform(click());
            onView(withId(R.id.crime_solved)).check(matches(isChecked()));
            // return to List, then check that solved is still selected in crimeFrag view
            Espresso.pressBack();

            onView(withId(R.id.crime_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(crimeID, click()));
            onView(withId(R.id.crime_solved)).check(matches(isChecked()));
            Espresso.pressBack();
        }
    }

    @Test
    public void testDateSelected(){

    }
}