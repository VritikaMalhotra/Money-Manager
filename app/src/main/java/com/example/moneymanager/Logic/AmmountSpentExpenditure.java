package com.example.moneymanager.Logic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymanager.R;

public class AmmountSpentExpenditure extends AppCompatActivity {

    EditText editTextEnterAmmountSpentExpenditure;
    Button buttonContinueAmmountSpentExpenditure;
    public static String AmmountSpentExpenditure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ammount_spent_expenditure);


        editTextEnterAmmountSpentExpenditure = findViewById(R.id.editTextEnterAmmountSpentExpenditure);
        buttonContinueAmmountSpentExpenditure = findViewById(R.id.buttonContinueAmmountSpentExpenditure);

        buttonContinueAmmountSpentExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmmountSpentExpenditure = editTextEnterAmmountSpentExpenditure.getText().toString();
                Toast.makeText(AmmountSpentExpenditure.this, AmmountSpentExpenditure, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AmmountSpentExpenditure.this, MakeEntryExpenditure.class);
                startActivity(intent);
            }
        });


    }
}