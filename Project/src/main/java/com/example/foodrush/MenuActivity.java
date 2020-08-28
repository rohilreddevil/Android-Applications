package com.example.foodrush;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    Button pad_thai, pad_thai_combo, burger, burger_combo, pizza, fries, soda;

    String order = "";
    int total = 0;
    int delivery_fee = 2;
    int quantity = 0;

    int attempts = 3;

    //quantities for each item
    int pad_thai_qty = 0;
    int burger_qty = 0;
    int pizza_qty = 0;
    int fries_qty = 0;
    int soda_qty = 0;

    //prices for each item
    int pad_thai_price = 0;
    int burger_price = 0;
    int pizza_price = 0;
    int fries_price = 0;
    int soda_price = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        pad_thai = (Button) findViewById(R.id.pad_thai_button);
        burger = (Button) findViewById(R.id.burger_button);
        pizza = (Button) findViewById(R.id.pizza_button);
        fries = (Button) findViewById(R.id.fries_button);
        soda = (Button) findViewById(R.id.soda_button);

        //wire up the reset button
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(onClickResetButton);

    }

    private View.OnClickListener onClickResetButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetMenu();
        }
    };

    public void resetMenu(){
        quantity = 0;
        total = 0;
        order = "";

        //reset prices
        pad_thai_price = 0;
        burger_price = 0;
        pizza_price = 0;
        fries_price = 0;
        soda_price = 0;

        //reset quantities
        pad_thai_qty = 0;
        burger_qty = 0;
        pizza_qty = 0;
        fries_qty = 0;
        soda_qty = 0;

    }


    public void addToOrder(View view){
       // price = 0;
        total = 0;

        if(view == findViewById(R.id.pad_thai_button)){
            order = order + "Pad Thai" + "\n";
            pad_thai_qty = pad_thai_qty +1;
            pad_thai_price= 10*pad_thai_qty;
            quantity = pad_thai_qty;
        }

       else if(view == findViewById(R.id.burger_button)){
            order = order + "Burger" + "\n";
            burger_qty = burger_qty+1;
            burger_price = 12*burger_qty;
            quantity = burger_qty;
        }


        else if(view == findViewById(R.id.pizza_button)){
            order = order + "Pizza" + "\n";
            pizza_qty = pizza_qty+1;
            pizza_price = 15*pizza_qty;
            quantity = pizza_qty;
        }

        else if(view == findViewById(R.id.fries_button)){
            order = order + "Fries" + "\n";
            fries_qty = fries_qty +1;
            fries_price = 3*fries_qty;
            quantity = fries_qty;
        }

        else if(view == findViewById(R.id.soda_button)){
            order = order + "Soda" + "\n";
            soda_qty = soda_qty +1;
            soda_price = 2*soda_qty;
            quantity = soda_qty;
        }

        quantity = pad_thai_qty+burger_qty + pizza_qty+fries_qty+soda_qty;

        total = pad_thai_price + burger_price + pizza_price + fries_price + soda_price
                + delivery_fee;

    }

    public void placeOrder(View view){
    //will redirect the user to the checkout page - new Intent

        TextView emptyCart = findViewById(R.id.error_message);
        Button confirmButton = findViewById(R.id.orderButton);

        if(quantity == 0){
            emptyCart.setText(R.string.empty_cart);
            emptyCart.setVisibility(View.VISIBLE);
            attempts = attempts -1;
            emptyCart.append(Integer.toString(attempts)); //display the number of attempts
                if(attempts == 0){
                    emptyCart.setText(R.string.lock_message);
                    confirmButton.setEnabled(false);
                }
        }

        else {
            attempts = 3;
            emptyCart.setVisibility(View.INVISIBLE);
            addToOrder(view);
            confirmButton.setEnabled(true);
            Intent intent = new Intent(this, Checkout.class);
            Bundle bundle =  new Bundle ();
            bundle.putString("order", order);
            bundle.putInt("total", total);
            bundle.putInt("quantity", quantity);

            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

}
