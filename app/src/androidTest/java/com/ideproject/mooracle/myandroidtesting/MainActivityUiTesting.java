package com.ideproject.mooracle.myandroidtesting;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityUiTesting {
    //set the rule of the test to center on Main Activity
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    //begin tests
    @Test
    public void testEditTextUpdateTextView() {
        //Arrange
        String givenString = "test 123";
        onView(withId(R.id.editText)).perform(typeText(givenString));
        //Act
        onView(withId(R.id.editText)).perform(pressImeActionButton());
        //Assert
        onView(withId(R.id.textView)).check(matches(withText(givenString)));
    }

    @Test
    public void spinnerUpdatesBackgroundColor(){
        //Arrange
        final int givenColor = Color.GREEN;
        String spinnerItemText = "Green";
        //Act
        onView(withId(R.id.colorSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(spinnerItemText))).perform(click());
        //Assert
        BoundedMatcher backgroundColorMatcher = new BoundedMatcher<View, ConstraintLayout>(ConstraintLayout.class) {
            @Override
            protected boolean matchesSafely(ConstraintLayout bg) {
                int actualColor = ((ColorDrawable)bg.getBackground()).getColor();
                return givenColor == actualColor;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("the background color supposed to be: " + givenColor);
            }
        };
        onView(withId(R.id.bg)).check(matches(backgroundColorMatcher));
    }

    @Test
    public void buttonLaunchesOtherActivity(){
        //Arrange
        String otherActivityString = "Other Activity";
        //Act
        onView(withId(R.id.launchActivityButton)).perform(click());
        //Assert
        onView(withText(otherActivityString)).check(matches(notNullValue()));
    }
}
