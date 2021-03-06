package com.example.moneymanager.Logic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moneymanager.R;

public class ChooseEntry extends AppCompatActivity {

    Button buttonIncome,buttonExpenditure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_entry);

        buttonIncome = findViewById(R.id.buttonIncome);
        buttonExpenditure = findViewById(R.id.buttonExpenditure);

        buttonIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DateIncome.class));
            }
        });

        buttonExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DateExpenditure.class));
            }
        });

    }
}