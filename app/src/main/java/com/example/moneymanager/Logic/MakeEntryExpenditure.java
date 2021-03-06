package com.example.moneymanager.Logic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.moneymanager.DBConnection.DBHelper;
import com.example.moneymanager.DBConnection.Entry;
import com.example.moneymanager.R;
import com.example.moneymanager.Session.SessionManagement;

public class MakeEntryExpenditure extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_entry_expenditure);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(MakeEntryExpenditure.this, "Yes clicked", Toast.LENGTH_SHORT).show();
                        makeEntry();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(MakeEntryExpenditure.this, "No clicked", Toast.LENGTH_SHORT).show();
                        OpenDisplay();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure about making the entry?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();


    }

    private void makeEntry() {

        SessionManagement sessionManagement = new SessionManagement(this);
        Entry entry = new Entry();
        entry.setEmail(sessionManagement.getSession());
        entry.setDate(DateExpenditure.DateExpenditure);
        entry.setItem_bought(PurposeExpenditure.PurposeExpenditure);
        entry.setAmmount_received("0");
        entry.setAmmount_spent(AmmountSpentExpenditure.AmmountSpentExpenditure);

        helper.insertEntry(entry);
        OpenDisplay();

    }

    private void OpenDisplay() {

        Intent intent = new Intent(MakeEntryExpenditure.this,Display.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}