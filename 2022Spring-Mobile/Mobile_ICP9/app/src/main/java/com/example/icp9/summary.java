package com.example.icp9;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class summary extends AppCompatActivity {

    private String customer;
    private String size;
    private int quantity;
    private ListView topping_display;
    private String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView quantity_size = findViewById(R.id.quantity_size);

        Intent intent = getIntent();
        customer = intent.getStringExtra("customer");
        size = intent.getStringExtra("size");
        quantity = intent.getIntExtra("quantity", 0);
        total = intent.getStringExtra("price");

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
        orderTotal.setText("Order Total: $" + total);
    }
}