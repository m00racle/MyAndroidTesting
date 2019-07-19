package com.ideproject.mooracle.myandroidtesting;

import android.graphics.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {
    MainActivityPresenter presenter;

    @Mock
    MainActivityView view;

    //since we already mock the MainActivityView interface as view we do not need the MockedView class

    @Before
    public void setUp() throws Exception {

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
        Mockito.verify(view).changeTextViewText(trialText);
    }

    @Test
    public void colorSelected() {
        //arrange
        int selectedInt = 2;

        //act
        presenter.colorSelected(selectedInt);
        //assert
        Mockito.verify(view).changeBackGroundColor(Color.GREEN);
    }

    @Test
    public void launchOtherActivityButtonClicked() {
        //Arrange
        Class clazz = OtherActivity.class;

        //Act
        presenter.launchOtherActivityButtonClicked(clazz);

        //Assert
        Mockito.verify(view).launchOtherActivity(clazz);
    }
}