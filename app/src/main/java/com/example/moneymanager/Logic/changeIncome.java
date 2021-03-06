package com.example.moneymanager.Logic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymanager.DBConnection.DBHelper;
import com.example.moneymanager.R;

public class changeIncome extends AppCompatActivity {

    EditText editTextEnterchangeIncome;
    Button buttonContinuechangeIncome;
    public static String changeIncome;
    DBHelper helper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_income);

        editTextEnterchangeIncome = findViewById(R.id.editTextEnterchangeIncome);
        buttonContinuechangeIncome = findViewById(R.id.buttonContinuechangeIncome);

        buttonContinuechangeIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeIncome = editTextEnterchangeIncome.getText().toString();
                String []value = CheckLogs.listvalue.split("\\s+");
                helper.changeEntries(value[0].trim(),value[1].trim(),value[2].trim(),"0");
                startActivity(new Intent(getApplicationContext(),CheckLogs.class));
            }
        });
    }
}