package com.example.moneymanager.Logic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.moneymanager.R;

import java.util.Calendar;

public class DateIncome extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEnterDate;
    Button buttonContinueDate;
    private int mYear, mMonth, mDay;
    public static String DateIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_income);

        editTextEnterDate = findViewById(R.id.editTextEnterDate);
        buttonContinueDate = findViewById(R.id.buttonContinueDate);

        editTextEnterDate.setOnClickListener(this);

        buttonContinueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateIncome = editTextEnterDate.getText().toString();
                Intent intent = new Intent(DateIncome.this, PurposeIncome.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View view) {

        if (view == editTextEnterDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            editTextEnterDate.setText(dayOfMonth + "/" + (monthOfYear + 1));

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
    }
}