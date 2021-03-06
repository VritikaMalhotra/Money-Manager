package com.example.moneymanager.Logic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.moneymanager.DBConnection.DBHelper;
import com.example.moneymanager.DBConnection.Entry;
import com.example.moneymanager.R;
import com.example.moneymanager.Session.SessionManagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class LogScreen extends AppCompatActivity {

    Button buttonCheckLogs,buttonClearLogs;
    DBHelper helper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_screen);

        buttonCheckLogs = findViewById(R.id.buttonCheckLogs);
        buttonClearLogs = findViewById(R.id.buttonClearLogs);

        buttonCheckLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CheckLogs.class));
            }
        });

        buttonClearLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:

                                String ammount_received = setAmmount();
                                helper.deleteEntries();
                                makeEntry(ammount_received);
                                Toast.makeText(LogScreen.this, "Yes clicked", Toast.LENGTH_SHORT).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                Toast.makeText(LogScreen.this, "No clicked", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(LogScreen.this);
                builder.setMessage("Do you want to clear the existing Entries?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }

        });
    }

    private void makeEntry(String ammount_received) {
        String date = new SimpleDateFormat("d/MM").format(Calendar.getInstance().getTime());
        SessionManagement sessionManagement = new SessionManagement(this);
        Entry entry = new Entry();
        entry.setEmail(sessionManagement.getSession());
        entry.setDate(date);
        entry.setItem_bought("Amt.Left");
        entry.setAmmount_received(ammount_received);
        entry.setAmmount_spent("0");

        helper.insertEntry(entry);

    }

    public String setAmmount(){
        ArrayList<String> entries;
        entries = helper.getEntries();
        int temp_ammount_received = 0;
        int temp_ammount_spent = 0;
        int ammount_received = 0;
        int ammount_spent = 0;
        String value = "";
        for(int i=0;i<entries.size();i++){
            String [] values = entries.get(i).split(" ");
            try{
                temp_ammount_received =  Integer.valueOf(values[2]);
                ammount_received = ammount_received + temp_ammount_received;
            }catch(Exception e){
                temp_ammount_received = 0;
            }

            try{
                temp_ammount_spent =  Integer.valueOf(values[3]);
                ammount_spent = ammount_spent + temp_ammount_spent;
            }catch(Exception e){
                temp_ammount_spent = 0;
            }
        }
        try{
            int val = ammount_received-ammount_spent;
            value = String.valueOf(val);
        }catch(Exception e){
            value = "";
        }
        return  value;
    }
}