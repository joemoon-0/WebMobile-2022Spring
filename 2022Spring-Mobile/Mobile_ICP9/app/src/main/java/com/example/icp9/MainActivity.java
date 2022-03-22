package com.example.icp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText subject;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        subject = findViewById(R.id.subjectText);
        message = findViewById(R.id.messageText);
        Button send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt = email.getText().toString();
                String[] reci = emailTxt.split(",");
                String subjectTxt = subject.getText().toString();
                String messageTxt = message.getText().toString();

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, emailTxt);
                intent.putExtra(Intent.EXTRA_SUBJECT, subjectTxt);
                intent.putExtra(Intent.EXTRA_TEXT, messageTxt);
                intent.setType("text/plain");

                startActivity(Intent.createChooser(intent, "How do you want to send your email?"));
            }
        });
    }
}