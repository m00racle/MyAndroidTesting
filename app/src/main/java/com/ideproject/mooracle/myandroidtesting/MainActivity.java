package com.ideproject.mooracle.myandroidtesting;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout bg;
    private EditText editText;
    private TextView textView;
    private Spinner colorSpinner;
    private Button launchActivityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        //create array adapter for spinner (learn more about this)
        colorSpinner = findViewById(R.id.colorSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colors_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        launchActivityButton = findViewById(R.id.launchActivityButton);
        bg = findViewById(R.id.bg);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    //if the action is done get the input text and put it into the text view
                    textView.setText(v.getText().toString());
                }
                return false;
            }
        });

        //set the color spinner selection listener
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //choose background white
                        bg.setBackgroundColor(Color.WHITE);
                        break;
                    case 1: //choose background Magenta
                        bg.setBackgroundColor(Color.MAGENTA);
                        break;
                    case 2: //choose background Green
                        bg.setBackgroundColor(Color.GREEN);
                        break;
                    case 3: //choose background Cyan
                        bg.setBackgroundColor(Color.CYAN);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
