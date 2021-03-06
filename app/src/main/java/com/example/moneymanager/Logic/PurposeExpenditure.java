package com.example.moneymanager.Logic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymanager.R;

public class PurposeExpenditure extends AppCompatActivity {

    EditText editTextEnterPurposeExpenditure;
    Button buttonContinuePurposeExpenditure;
    public static String PurposeExpenditure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purpose_expenditure);

        editTextEnterPurposeExpenditure = findViewById(R.id.editTextEnterPurposeExpenditure);
        buttonContinuePurposeExpenditure = findViewById(R.id.buttonContinuePurposeExpenditure);

        buttonContinuePurposeExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurposeExpenditure = editTextEnterPurposeExpenditure.getText().toString().replaceAll("\\s+", "_");
                Toast.makeText(PurposeExpenditure.this, PurposeExpenditure, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PurposeExpenditure.this, AmmountSpentExpenditure.class);
                startActivity(intent);
            }
        });
    }
}