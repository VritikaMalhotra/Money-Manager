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

public class DateExpenditure extends AppCompatActivity implements View.OnClickListener  {

    EditText editTextEnterDateExpenditure;
    Button buttonContinueDateExpenditure;
    private int mYear, mMonth, mDay;
    public static String DateExpenditure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_expenditure);

        editTextEnterDateExpenditure = findViewById(R.id.editTextEnterDateExpenditure);
        buttonContinueDateExpenditure = findViewById(R.id.buttonContinueDateExpenditure);

        editTextEnterDateExpenditure.setOnClickListener(this);

        buttonContinueDateExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateExpenditure = editTextEnterDateExpenditure.getText().toString();
                Intent intent = new Intent(DateExpenditure.this, PurposeExpenditure.class);
                startActivity(intent);
            }
        });

    }

    public void onClick(View view) {

        if (view == editTextEnterDateExpenditure) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            editTextEnterDateExpenditure.setText(dayOfMonth + "/" + (monthOfYear + 1));

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}