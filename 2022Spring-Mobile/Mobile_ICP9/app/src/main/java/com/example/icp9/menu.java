package com.example.icp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class menu extends AppCompatActivity {

    final double SMALL_PRICE = 7.99;
    final double MEDIUM_PRICE = 9.99;
    final double LARGE_PRICE = 11.99;
    final double TAX_RATE = 1.07;
    double subtotal = 0.0;

    EditText customername;
    String pizza_size;

    private NumberPicker quantityPicker;
    final ArrayList<String> toppings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button small = findViewById(R.id.small);
        Button medium = findViewById(R.id.medium);
        Button large = findViewById(R.id.large);
        Button summary = findViewById(R.id.summary);
        Button order = findViewById(R.id.order);

        customername= findViewById(R.id.name);
        quantityPicker = findViewById(R.id.quantity);
        quantityPicker.setMinValue(1);
        quantityPicker.setMaxValue(99);

        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtotal = SMALL_PRICE;
                pizza_size = "Small";
                Toast.makeText(menu.this, "Small selected.", Toast.LENGTH_SHORT).show();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtotal = MEDIUM_PRICE;
                pizza_size = "Medium";
                Toast.makeText(menu.this, "Medium selected.", Toast.LENGTH_SHORT).show();
            }
        });

        large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtotal = LARGE_PRICE;
                pizza_size = "Large";
                Toast.makeText(menu.this, "Large selected.", Toast.LENGTH_SHORT).show();
            }
        });

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, summary.class);
                intent.putExtra("name", customername.toString());
                intent.putExtra("quantity", quantityPicker.getValue());
                intent.putExtra("size", pizza_size);
                intent.putStringArrayListExtra("toppings", getToppings());
                intent.putExtra("price", calculateTotal(subtotal));
                startActivity(intent);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(menu.this, "ORDER", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public String calculateTotal(double subtotal) {
        DecimalFormat priceFormat = new DecimalFormat("0.00");
        return priceFormat.format(subtotal * TAX_RATE * quantityPicker.getValue());
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
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
            case R.id.mushroom:
                if (checked)
                    toppings.add("Mushrooms");
                else
                    toppings.remove(toppings.indexOf("Mushrooms"));
                break;
            case R.id.gpepper:
                if (checked)
                    toppings.add("Green Peppers");
                else
                    toppings.remove(toppings.indexOf("Green Peppers"));
                    break;
            case R.id.olives:
                if (checked)
                    toppings.add("Olives");
                else
                    toppings.remove(toppings.indexOf("Olives"));
                break;
            case R.id.sausage:
                if (checked)
                    toppings.add("Sausage");
                else
                    toppings.remove(toppings.indexOf("Sausage"));
                break;
            default:
                Toast.makeText(menu.this, "ERROR: toppings not registering", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}