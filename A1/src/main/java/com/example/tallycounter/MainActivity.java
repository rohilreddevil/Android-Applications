package com.example.tallycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button; //this class enables button functionality
import android.widget.TextView; //this enables text view functionality

public class MainActivity extends AppCompatActivity {

    private TextView countTextView; //declare the object at class-level scope
    private int count; // stores the current count value

    @Override
    protected void onCreate(Bundle savedInstanceState) { //this method loads the the UI we set up
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = 0; //initialize it to 0

        //wire up another event to reference the button and the text view
        Button countButton = findViewById(R.id.countButton); //object declaration of Button type
        countTextView = findViewById(R.id.countTextView); //initialize the object
        Button resetButton = findViewById(R.id.resetButton); // initialize the reset object


        //now add click listeners for click me and the reset buttons
        countButton.setOnClickListener(onClickCountButton);

        resetButton.setOnClickListener(onClickResetButton);
    }
    //declare a function that can be triggered WHEN the button is CLICKED
    //subsequently, the onClick() method will be called

    private View.OnClickListener onClickCountButton = new View.OnClickListener(){

        @Override
        public void onClick(View v) { //increment the value when clicked
            //increment the counter and set the text on the text view
            count++;
            countTextView.setText(String.valueOf(count)); //convert 'count' into a string
        }
    };

    //wire up an event for the reset button's functionality
    private View.OnClickListener onClickResetButton = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            count = 0;
            countTextView.setText(String.valueOf(count)); //convert 'count' into a string
        }
    };


}
