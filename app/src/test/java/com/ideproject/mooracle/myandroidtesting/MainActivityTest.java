package com.ideproject.mooracle.myandroidtesting;

import android.view.inputmethod.EditorInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

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
}
