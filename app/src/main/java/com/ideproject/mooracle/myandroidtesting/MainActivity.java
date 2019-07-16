package com.ideproject.mooracle.myandroidtesting;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements MainActivityView{
    private ConstraintLayout bg;
    TextView textView;
    EditText editText;

    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);

        textView = findViewById(R.id.textView);

        editText = findViewById(R.id.editText);

        //create array adapter for spinner (learn more about this)
        Spinner colorSpinner = findViewById(R.id.colorSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colors_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        Button launchActivityButton = findViewById(R.id.launchActivityButton);
        bg = findViewById(R.id.bg);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    //if the action is done get the input text and put it into the text view
                    String text = v.getText().toString();
                    presenter.editTextUpdated(text);
                }
                return false;
            }
        });

        //set the color spinner selection listener
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // updated using MainActivityPresenter
                presenter.colorSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        launchActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // use the presenter
                presenter.launchOtherActivityButtonClicked(OtherActivity.class);
            }
        });
    }

//    private void startOtherActivity() {
//        Intent startOtherActivityIntent = new Intent(this, OtherActivity.class);
//        startActivity(startOtherActivityIntent);
//    } //<--unused from now on since we use the MainActivityPresenter class method

    @Override
    public void changeTextViewText(String text) {
        textView.setText(text);
    }

    @Override
    public void changeBackGroundColor(int color) {
        bg.setBackgroundColor(color);
    }

    @Override
    public void launchOtherActivity(Class activity) {
        Intent startOtherActivityIntent = new Intent(this, OtherActivity.class);
        startActivity(startOtherActivityIntent);
    }
}
