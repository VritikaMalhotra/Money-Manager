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

public class changeExpense extends AppCompatActivity {

    EditText editTextEnterchangeExpense;
    Button buttonContinuechangeExpense;
    public static String changeExpense;
    DBHelper helper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_expense);

        editTextEnterchangeExpense = findViewById(R.id.editTextEnterchangeExpense);
        buttonContinuechangeExpense = findViewById(R.id.buttonContinuechangeExpense);

        buttonContinuechangeExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeExpense = editTextEnterchangeExpense.getText().toString();
                String []value = CheckLogs.listvalue.split("\\s+");
                helper.changeEntries(value[0].trim(),value[1].trim(),"0",value[2].trim());
                startActivity(new Intent(getApplicationContext(),CheckLogs.class));
            }
        });
    }

}