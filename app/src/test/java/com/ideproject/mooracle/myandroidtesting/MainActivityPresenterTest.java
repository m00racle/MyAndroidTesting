package com.ideproject.mooracle.myandroidtesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityPresenterTest {
    MainActivityPresenter presenter;
    MainActivityView view;

    class MockedView implements MainActivityView {
        String textViewText;
        @Override
        public void changeTextViewText(String text) {
            textViewText = text;
        }

        @Override
        public void changeBackGroundColor(int color) {

        }

        @Override
        public void launchOtherActivity(Class activity) {

        }
    }

    @Before
    public void setUp() throws Exception {
        //implements view using mocked view class
        view = new MockedView();
        //use view to instantiate presenter
        presenter = new MainActivityPresenter(view);
    }

    @Test
    public void editTextUpdated() {
        //arrange
        String trialText = "hello 123";

        //act
        presenter.editTextUpdated(trialText);

        //assert
        Assert.assertEquals(trialText, ((MockedView)view).textViewText);
    }

    @Test
    public void colorSelected() {
    }

    @Test
    public void launchOtherActivityButtonClicked() {
    }
}