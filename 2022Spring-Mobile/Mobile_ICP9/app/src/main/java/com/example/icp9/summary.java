package com.example.icp9;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class summary extends AppCompatActivity {

    private String customer, size, subtotal;
    private int quantity;
    private ListView topping_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView customerGreet = findViewById(R.id.customer);
        TextView quantity_size = findViewById(R.id.quantity_size);
        Button order = findViewById(R.id.order);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if (extras != null) {
            customer = extras.getString("name");// getStringExtra("name");
            size = extras.getString("size"); //intent.getStringExtra("size");
            quantity = extras.getInt("quantity"); //intent.getIntExtra("quantity", 0);
            subtotal = extras.getString("price"); //intent.getStringExtra("price");

            customerGreet.setText("Hello " + customer);
            quantity_size.setText("You ordered " + quantity + " " + size + " pizzas.");

            topping_display = (ListView) findViewById(R.id.toppings_list);
            ArrayList<String> topping_array = intent.getStringArrayListExtra("toppings");
            ArrayAdapter<String> topping_adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    topping_array
            );
            topping_display.setAdapter(topping_adapter);

            TextView orderTotal = findViewById(R.id.total);
            orderTotal.setText("Order Subtotal: $" + subtotal);

            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(summary.this, menu.class);
                    intent.putExtra("name", customer);
                    intent.putExtra("quantity", quantity);
                    intent.putExtra("size", size);
                    intent.putStringArrayListExtra("topping_array", topping_array);
                    startActivity(intent);
                }
            });
        }
        else {
            Toast.makeText(summary.this, "ERROR: null data - restart application", Toast.LENGTH_SHORT).show();
        }
    }
}