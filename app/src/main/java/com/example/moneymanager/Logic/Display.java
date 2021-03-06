package com.example.moneymanager.Logic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.R;
import com.example.moneymanager.Session.SessionManagement;
import com.example.moneymanager.Session.SignIn;

public class Display extends AppCompatActivity {

    Button buttonLogout,buttonEntry,buttonLogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        SessionManagement sessionManagement = new SessionManagement(Display.this);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonEntry = findViewById(R.id.buttonEntry);
        buttonLogs = findViewById(R.id.buttonLogs);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Will remove session and open login screen.

                SessionManagement sessionManagement = new SessionManagement(Display.this);
                sessionManagement.removeSession();
                Toast.makeText(Display.this, "User logged out.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), SignIn.class));
                finish();
            }
        });
        buttonEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Display.this, "Creating new entry", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ChooseEntry.class));
            }
        });
        buttonLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LogScreen.class));
            }
        });
    }
}