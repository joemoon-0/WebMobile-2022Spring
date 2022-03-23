package com.example.icp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class menu extends AppCompatActivity {

    final double SMALL_PRICE = 7.99;
    final double MEDIUM_PRICE = 9.99;
    final double LARGE_PRICE = 11.99;
    final double TAX_RATE = 1.07;
    double subtotal = 0.0;

//    EditText customername = findViewById(R.id.name);
//    CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni);
//    CheckBox cheese = (CheckBox) findViewById(R.id.cheese);
//    CheckBox mushroom = (CheckBox) findViewById(R.id.mushroom);
//    CheckBox gpepper = (CheckBox) findViewById(R.id.gpepper);
//    CheckBox olives = (CheckBox) findViewById(R.id.olives);
//    CheckBox sausage = (CheckBox) findViewById(R.id.sausage);

    ArrayList<String> toppings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button small = findViewById(R.id.small);
        Button medium = findViewById(R.id.medium);
        Button large = findViewById(R.id.large);
        Button summary = findViewById(R.id.summary);
        Button order = findViewById(R.id.order);

        toppings = new ArrayList<>();

        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtotal = SMALL_PRICE;
                Toast.makeText(menu.this, "Small selected.", Toast.LENGTH_SHORT).show();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtotal = MEDIUM_PRICE;
                Toast.makeText(menu.this, "Medium selected.", Toast.LENGTH_SHORT).show();
            }
        });

        large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtotal = LARGE_PRICE;
                Toast.makeText(menu.this, "Large selected.", Toast.LENGTH_SHORT).show();
            }
        });

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, summary.class);
                intent.putExtra("price", calculateTotal(subtotal));
                intent.putStringArrayListExtra("toppings", getToppings());
                Toast.makeText(menu.this, TextUtils.join(", ", getToppings()), Toast.LENGTH_SHORT).show();
               // startActivity(intent);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.pepperoni:
                if (checked)
                    toppings.add("Pepperoni");
                else
                    toppings.remove(toppings.indexOf("Pepperoni"));
                break;
            case R.id.cheese:
                if (checked)
                    toppings.add("Extra Cheese");
                else
                    toppings.remove(toppings.indexOf("Extra Cheese"));
                break;
        }
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

//    public ArrayList<String> getToppings(View view) {
//        toppings = new ArrayList<>();
//        boolean checked = ((CheckBox) view).isChecked();
//
//        switch(view.getId()) {
//            case R.id.pepperoni:
//                if (checked)
//                    toppings.add("Pepperoni");
//                else
//                    toppings.remove(toppings.indexOf("Pepperoni"));
//                break;
//            case R.id.cheese:
//                if (checked)
//                    toppings.add("Extra Cheese");
//                else
//                    toppings.remove(toppings.indexOf("Extra Cheese"));
//                break;
//        }
//        if (pepperoni.isChecked()) {
//            toppings.add("Pepperoni");
//        }
//        if (cheese.isChecked()) {
//            toppings.add("Extra Cheese");
//        }
//        if (olives.isChecked()) {
//            toppings.add("Olives");
//        }
//        if (mushroom.isChecked()) {
//            toppings.add("Mushroom");
//        }
//        if (gpepper.isChecked()) {
//            toppings.add("Green Peppers");
//        }
//        if (sausage.isChecked()) {
//            toppings.add("Sausage");
//        }

//        return toppings;
//    }

    public double calculateTotal(double subtotal) {
        return subtotal * TAX_RATE;
    }
}