package com.github.anselmos.popularmovies;

import com.github.anselmos.popularmovies.activities.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by anselmos on 21.04.17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    
    ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    
    @Test
    public void mainAcitivityLayoutVisible() {
        activityTestRule.launchActivity(new Intent());
        onView(withId(R.id.poster_grid)).perform(click()).check(matches(isDisplayed()));
    }
}
