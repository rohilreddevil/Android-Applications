package com.example.foodrush;

import  androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private int count = 3; //regulate the number of attempts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get access to the button
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(onClickLoginButton);
    }


    //set up the listener for the button

    private View.OnClickListener onClickLoginButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText userNameEditText = findViewById(R.id.userNameEditText);
            EditText passwordEditText = findViewById(R.id.passwordEditText); //grant access to the control

            TextView errorTextView = findViewById(R.id.errorTextView);

            //create 2 strings to obtain the TEXT
            //getText() will return an EDITABLE, NOT STRING
            //Hence, it is important to use "toString()" at the end of the line

            String userName = userNameEditText.getText().toString(); //obtain the username
            String password = passwordEditText.getText().toString(); //obtain the password

            //instantiate Login Manager
            LoginManager loginManager = new LoginManager(userName, password);

            //now VALIDATE THE CREDENTIALS
            if(loginManager.hasValidCredentials()){

                displayMenu(v);
                count = 3; //reset the number of attempts
                errorTextView.setVisibility(View.INVISIBLE); //success message will be invisible
                //displayMenu();


            } else{
                count = count - 1;

                errorTextView.setText(R.string.error_text); //set a string for the error message
                errorTextView.setVisibility(View.VISIBLE); //failure message will be visible
                errorTextView.append(Integer.toString(count)); //display the number of attempts

                if(count == 0){
                    Button loginButton = findViewById(R.id.loginButton);
                    errorTextView.setText(R.string.lock_message);
                    loginButton.setEnabled(false); //lock the user
                }

            }

        }
    };


    public void displayMenu(View view){
        //this method will be called when the user clicks the login button
        //redirected to the menu page
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);

    }

    /*public void displayMenu(){
        //this method will be called when the user clicks the login button
        //redirected to the menu page
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    } */

}
