package com.example.moneymanager.Logic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.DBConnection.DBHelper;
import com.example.moneymanager.ListView.DataProvider;
import com.example.moneymanager.ListView.ListAdapter;
import com.example.moneymanager.R;

import java.util.ArrayList;
import java.util.Collections;

public class CheckLogs extends AppCompatActivity {

    ListView listView;
    ListAdapter listAdapter;
    TextView textViewAmmountReceived;
    TextView textViewAmmountSpent;
    TextView textViewAmmountLeft;
    ArrayList<String> entries = new ArrayList<>();
    ArrayList<String> entry = new ArrayList<>();
    DBHelper helper = new DBHelper(this);
    public static String listvalue;
    public static int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_logs);
        listView = findViewById(R.id.listView);
        textViewAmmountReceived = findViewById(R.id.textViewAmmountReceived);
        textViewAmmountSpent = findViewById(R.id.textViewAmmountSpent);
        textViewAmmountLeft = findViewById(R.id.textViewAmmountLeft);
        setValue();

        listAdapter = new ListAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(listAdapter);

        entries = helper.getEntries();
        Collections.reverse(entries);

        for(int i=0;i<entries.size();i++){
            String[] values = entries.get(i).split(" ");
            if(values[2].equals("0")){
                values[2] = " ";
            }
            if(values[3].equals("0")){
                values[3] = " ";
            }
            //This is og one.
            //String temp = String.format("%-12s %-10s %-7s %-6s",values[0].trim(),values[1].trim(),values[2].trim(),values[3].trim()).replace(" ", "  ");
            //String temp = String.format("%-14s %-14s %-5s %-5s",values[0].trim(),values[1].trim(),values[2].trim(),values[3].trim()).replace(" ", "  ");

           // entry.add(temp);

            DataProvider dataProvider = new DataProvider(values[0],values[1],values[2],values[3]);
            listAdapter.add(dataProvider);
        }

        //ArrayAdapter<String> entryAdapter = new ArrayAdapter<>(this,R.layout.textview,entry);
        //listView.setAdapter(entryAdapter);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                listvalue = listView.getItemAtPosition(i).toString();
                Toast.makeText(CheckLogs.this, listvalue, Toast.LENGTH_SHORT).show();
                index = i;
                firstDialog();

            }
        });*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view = listView.getChildAt(position);
                String date = ((TextView) view.findViewById(R.id.date)).getText().toString();
                String purpose = ((TextView) view.findViewById(R.id.purpose)).getText().toString();
                String income = ((TextView) view.findViewById(R.id.income)).getText().toString();
                String expenditure = ((TextView) view.findViewById(R.id.expenditure)).getText().toString();
                listvalue = date+" "+purpose+" "+income+" "+expenditure;
                firstDialog();
            }
        });

    }

    private void firstDialog(){

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(CheckLogs.this, "Delete", Toast.LENGTH_SHORT).show();
                        checkDeletion();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(CheckLogs.this, "Change", Toast.LENGTH_SHORT).show();
                        changeValue();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(CheckLogs.this);
        builder.setMessage("Choose of them.").setPositiveButton("Delete", dialogClickListener)
                .setNegativeButton("Change", dialogClickListener).show();
    }

    private void checkDeletion() {
        final String []value = CheckLogs.listvalue.split("\\s+");
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(CheckLogs.this, "YES", Toast.LENGTH_SHORT).show();
                        helper.deleteFromList(value[0].trim(),value[1].trim());
                        startActivity(new Intent(getApplicationContext(),CheckLogs.class));
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(CheckLogs.this, "NO", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(CheckLogs.this);
        builder.setMessage("Delete all entries with date "+value[0]+" purpose "+value[1]).setPositiveButton("YES", dialogClickListener)
                .setNegativeButton("NO", dialogClickListener).show();


    }

    private void changeValue() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(CheckLogs.this, "Income", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),changeIncome.class));
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(CheckLogs.this, "Expenditure", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),changeExpense.class));
                        finish();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(CheckLogs.this);
        builder.setMessage("Choose of them.").setPositiveButton("Income", dialogClickListener)
                .setNegativeButton("Expenditure", dialogClickListener).show();

    }

    private void setValue(){
        ArrayList<String> entries;
        entries = helper.getEntries();
        int temp_ammount_received = 0;
        int temp_ammount_spent = 0;
        int ammount_received = 0;
        int ammount_spent = 0;
        String temp_ar;
        String temp_as;
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

        int ammount_left = ammount_received-ammount_spent;
        String temp_al = "";

        try{
            temp_ar = String.valueOf(ammount_received);
            String temp = textViewAmmountReceived.getText().toString();
            textViewAmmountReceived.setText(temp+temp_ar);
        }catch(Exception e){
            temp_ar = "";
        }
        try{
            temp_as = String.valueOf(ammount_spent);
            String temp = textViewAmmountSpent.getText().toString();
            textViewAmmountSpent.setText(temp+temp_as);
        }catch(Exception e){
            temp_as = "";
        }
        try{
            temp_al = String.valueOf(ammount_left);
            String temp = textViewAmmountLeft.getText().toString();
            textViewAmmountLeft.setText(temp+temp_al);
        }catch(Exception e){
            temp_al = "";
        }

    }
}