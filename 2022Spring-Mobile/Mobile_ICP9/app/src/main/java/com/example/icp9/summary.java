package com.example.icp9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class summary extends AppCompatActivity {

    ListView lv = (ListView) findViewById(R.id.toppings_list);
    //ArrayList<String> toppings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            double price = extras.getDouble("price");
            String price_str = String.format("%.2f", price);
            Toast.makeText(summary.this, price_str, Toast.LENGTH_SHORT).show();
            ArrayList<String> toppings = extras.getStringArrayList("toppings");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toppings);
            lv.setAdapter(arrayAdapter);
        }


    }
}