package com.example.moneymanager.Logic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymanager.R;

public class AmmountReceivedIncome extends AppCompatActivity {

    EditText editTextEnterAmmountReceivedIncome;
    Button buttonContinueAmmountReceivedIncome;
    public static String AmmountReceivedIncome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ammount_received_income);

        editTextEnterAmmountReceivedIncome = findViewById(R.id.editTextEnterAmmountReceivedIncome);
        buttonContinueAmmountReceivedIncome = findViewById(R.id.buttonContinueAmmountReceivedIncome);

        buttonContinueAmmountReceivedIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmmountReceivedIncome = editTextEnterAmmountReceivedIncome.getText().toString();
                Toast.makeText(AmmountReceivedIncome.this, AmmountReceivedIncome, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AmmountReceivedIncome.this, MakeEntryIncome.class);
                startActivity(intent);
            }
        });
    }
}