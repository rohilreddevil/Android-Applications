package com.example.foodrush;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Checkout extends AppCompatActivity {

    TextView ordersView, priceView, quantityView;
    String orders_list;
    int total_price;
    int quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

         ordersView = (TextView) findViewById(R.id.orders);
         priceView = (TextView) findViewById(R.id.displayPrice);
         quantityView = (TextView) findViewById(R.id.quantity);

         Bundle bundle = getIntent().getExtras();
         orders_list = bundle.getString("order");
         quantity = bundle.getInt("quantity");
         total_price = bundle.getInt("total");
         ordersView.setText(orders_list);
         ordersView.append("Delivery (2$)");

         quantityView.append(Integer.toString(quantity));
         priceView.append(Integer.toString(total_price));

        //get access to the button
        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(onCLickConfirmButton);
    }

    private View.OnClickListener onCLickConfirmButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           thankYou(v);
        }
    };


    public void thankYou(View view){
        //will redirect the user back to the menu

        Intent intent = new Intent(this, thankYou.class);
        startActivity(intent);

    }

}