package com.example.zo0okadev.wunderdemo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class CarsListActivityTest {

    @Rule
    public ActivityTestRule<CarsListActivity> activityTestRule = new ActivityTestRule<>(CarsListActivity.class);

    @Test
    public void checkCarNameInListItem() {
        onData(anything()).inAdapterView(withId(R.id.carListView)).atPosition(0).onChildView(withId(R.id.carNameTextView))
                .check(matches(withText("HH-GO8522")));
    }

}
