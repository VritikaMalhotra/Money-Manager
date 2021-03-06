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

public class MakeEntryIncome extends AppCompatActivity {

    int amReceived = 0,amSpent = 0;
    DBHelper helper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_entry);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(MakeEntryIncome.this, "Yes clicked", Toast.LENGTH_SHORT).show();
                        makeEntry();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(MakeEntryIncome.this, "No clicked", Toast.LENGTH_SHORT).show();
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
        entry.setDate(DateIncome.DateIncome);
        entry.setItem_bought(PurposeIncome.Purpose);
        entry.setAmmount_received(AmmountReceivedIncome.AmmountReceivedIncome);
        entry.setAmmount_spent("0");

        helper.insertEntry(entry);
        OpenDisplay();

    }

    private void balanceAmmount(String ammountReceived, String ammountSpent) {

        int previous_ammount_received = 0;
        int previous_ammount_spent = 0;

        try{
            amReceived = Integer. valueOf(ammountReceived);
        }catch (Exception e){
            amReceived = 0;
        }
        try{
            amSpent = Integer.valueOf(ammountSpent);
        }catch (Exception e){
            amSpent = 0;
        }

        String ammount = helper.getPreviousEntry();
        String[] values = ammount.split(" ");
        try{
            previous_ammount_received = Integer.valueOf(values[0]);
        }catch (Exception e){
            previous_ammount_received = 0;
        }
        try{
            previous_ammount_spent = Integer.valueOf(values[1]);
        }catch (Exception e){
            previous_ammount_spent = 0;
        }

        amReceived = amReceived+previous_ammount_received;
        amSpent = amSpent+previous_ammount_spent;

    }

    private void OpenDisplay() {

        Intent intent = new Intent(MakeEntryIncome.this,Display.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}