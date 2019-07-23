package com.ideproject.mooracle.myandroidtesting;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.inputmethod.EditorInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    // create the MainActivity class instance and set it beforehand
    private MainActivity mainActivity;
    @Before
    public void setUp(){
        mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    // test the text editor views edit finish to see if ot will be shown in the text view
    @Test
    public void editTextUpdateTextView() throws Exception {
        //Arrange
        String givenString = "test 123";
        //NOTE: make the editText variable package private first to be accessed from here!!
        mainActivity.editText.setText(givenString);

        //Act: set the onEditorAction method from Anonymous class TextView.OnEditorActionListener
        mainActivity.editText.onEditorAction(EditorInfo.IME_ACTION_DONE);

        //Assert
        String actualString = mainActivity.textView.getText().toString();
        assertEquals(givenString,actualString);
    }

    @Test
    public void testSpinnerChangeBackgroundColor() throws Exception{
        //Arrange
        int index = 2;
        int givenColor = Color.GREEN;

        //Act
        mainActivity.colorSpinner.setSelection(index);

        //Assert
        int actualColor = ((ColorDrawable)mainActivity.bg.getBackground()).getColor();
        assertEquals(givenColor, actualColor);
    }

    @Test
    public void testButtonLaunchedOtherActivity() throws Exception {
        //Arrange
        Class clazz = OtherActivity.class;
        Intent expectedIntent = new Intent(mainActivity, clazz);

        //Act
        mainActivity.launchActivityButton.callOnClick();

        //Assert
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }
}
