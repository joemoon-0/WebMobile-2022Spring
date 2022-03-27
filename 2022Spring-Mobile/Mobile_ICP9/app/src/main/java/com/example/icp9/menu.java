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
    double price = 0.0;

    EditText customername;
    String pizza_size, total;
    CheckBox pepperoni, cheese, gpepper, olives, mushroom, sausage;

    private NumberPicker quantityPicker;
    ArrayList<String> toppings = new ArrayList<>();

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

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            // Reads existing customer options and restores UI to that state
            customername.setText(extras.getString("name"));
            quantityPicker.setValue(extras.getInt("quantity"));
            pizza_size = extras.getString("size");
            switch (pizza_size) {
                case "Small":
                    price = SMALL_PRICE;
                    break;
                case "Medium":
                    price = MEDIUM_PRICE;
                    break;
                case "Large":
                    price = LARGE_PRICE;
                    break;
                default:
                    break;
            }

            toppings = new ArrayList<String>(extras.getStringArrayList("topping_array"));
            if (toppings.contains("Pepperoni")) {
                pepperoni = findViewById(R.id.pepperoni);
                pepperoni.setChecked(true);
            }
            if (toppings.contains("Extra Cheese")) {
                cheese = findViewById(R.id.cheese);
                cheese.setChecked(true);
            }
            if (toppings.contains("Mushrooms")) {
                mushroom = findViewById(R.id.mushroom);
                mushroom.setChecked(true);
            }
            if (toppings.contains("Green Peppers")) {
                gpepper = findViewById(R.id.gpepper);
                gpepper.setChecked(true);
            }
            if (toppings.contains("Olives")) {
                olives = findViewById(R.id.olives);
                olives.setChecked(true);
            }
            if (toppings.contains("Sausage")) {
                sausage = findViewById(R.id.sausage);
                sausage.setChecked(true);
            }
        }

        // *** CLICK LISTENERS ***

        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price = SMALL_PRICE;
                pizza_size = "Small";
                Toast.makeText(menu.this, "Small selected.", Toast.LENGTH_SHORT).show();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price = MEDIUM_PRICE;
                pizza_size = "Medium";
                Toast.makeText(menu.this, "Medium selected.", Toast.LENGTH_SHORT).show();
            }
        });

        large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price = LARGE_PRICE;
                pizza_size = "Large";
                Toast.makeText(menu.this, "Large selected.", Toast.LENGTH_SHORT).show();
            }
        });

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, summary.class);
                intent.putExtra("name", customername.getText().toString());
                intent.putExtra("quantity", quantityPicker.getValue());
                intent.putExtra("size", pizza_size);
                intent.putStringArrayListExtra("toppings", toppings);
                intent.putExtra("price", calculateTotal(price));
                startActivity(intent);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, MainActivity.class);
                intent.putExtra("name", customername.getText().toString());
                intent.putExtra("quantity", quantityPicker.getValue());
                intent.putExtra("size", pizza_size);
                intent.putStringArrayListExtra("toppings", toppings);
                intent.putExtra("price", calculateTotal(price));
                startActivity(intent);
            }
        });
    }

    public String calculateTotal(double price) {
        DecimalFormat priceFormat = new DecimalFormat("0.00");
        return priceFormat.format(price * TAX_RATE * quantityPicker.getValue());
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