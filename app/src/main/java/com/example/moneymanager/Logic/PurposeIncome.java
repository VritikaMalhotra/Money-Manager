package com.example.moneymanager.Logic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymanager.R;

public class PurposeIncome extends AppCompatActivity {

    EditText editTextEnterPurpose;
    Button buttonContinuePurpose;
    public static String Purpose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purpose_income);

        editTextEnterPurpose = findViewById(R.id.editTextEnterPurpose);
        buttonContinuePurpose = findViewById(R.id.buttonContinuePurpose);

        buttonContinuePurpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Purpose = editTextEnterPurpose.getText().toString().replaceAll("\\s+", "_");
                Toast.makeText(PurposeIncome.this, Purpose, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PurposeIncome.this, AmmountReceivedIncome.class);
                startActivity(intent);
            }
        });
    }
}