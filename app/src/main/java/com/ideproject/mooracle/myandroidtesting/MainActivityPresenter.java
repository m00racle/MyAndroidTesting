package com.ideproject.mooracle.myandroidtesting;

import android.graphics.Color;

public class MainActivityPresenter {
    MainActivityView view;

    //constructor
    public MainActivityPresenter(MainActivityView view){
        this.view = view;
    }

    //methods
    public void editTextUpdated(String text){
        view.changeTextViewText(text);
    }

    public void colorSelected(int position){
        switch (position) {
            case 0: //choose background white
                view.changeBackGroundColor(Color.WHITE);
                break;
            case 1: //choose background Magenta
                view.changeBackGroundColor(Color.MAGENTA);
                break;
            case 2: //choose background Green
                view.changeBackGroundColor(Color.GREEN);
                break;
            case 3: //choose background Cyan
                view.changeBackGroundColor(Color.CYAN);
                break;
        }
    }

    public void launchOtherActivityButtonClicked(Class activity){
        view.launchOtherActivity(activity);
    }
}
