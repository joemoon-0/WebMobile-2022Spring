package com.example.icp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText subject;
    EditText message;

    private String customer, size, total, topping_list = "";
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        TextView summary = findViewById(R.id.summary);
        Button send = findViewById(R.id.send);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            customer = extras.getString("name");// getStringExtra("name");
            size = extras.getString("size"); //intent.getStringExtra("size");
            quantity = extras.getInt("quantity"); //intent.getIntExtra("quantity", 0);
            total = extras.getString("price");
            ArrayList<String> topping_array = new ArrayList<String>(extras.getStringArrayList("toppings"));
            topping_list = TextUtils.join("\n", topping_array);

            summary.setText("Hello " + customer + ".  Your order is as follows...\n\n " +
                    "You ordered " + quantity + " " + size + " pizzas with the following toppings:\n\n" +
                    topping_list +"\n\nOrder Subtotal: $" + total);
        }
        else {
            Toast.makeText(MainActivity.this, "ERROR: null data - restart application", Toast.LENGTH_SHORT).show();
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt = email.getText().toString();
                // String[] reci = emailTxt.split(",");
                String subjectTxt = "Your Pizza Order";
                String messageTxt = summary.getText().toString();

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, emailTxt);
                intent.putExtra(Intent.EXTRA_SUBJECT, subjectTxt);
                intent.putExtra(Intent.EXTRA_TEXT, messageTxt);
                intent.setType("text/plain");

                startActivity(Intent.createChooser(intent, "How do you want to send your email?"));
            }
        });
    }
}