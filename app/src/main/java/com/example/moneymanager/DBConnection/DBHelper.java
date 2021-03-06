package com.example.moneymanager.DBConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.moneymanager.Logic.changeExpense;
import com.example.moneymanager.Logic.changeIncome;
import com.example.moneymanager.Session.SessionManagement;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public Context context;

    //Session Table
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MM.db";
    private static final String TABLE_NAME = "session_manager";
    private static final String COULMN_ID = "id";
    private static final String COULMN_NAME = "name";
    private static final String COULMN_EMAIL = "email";
    private static final String COULMN_PASSWORD = "password";

    private static final String TABLE_CREATE = "create table " + TABLE_NAME + " (id integer primary key not null  , " +
            "name text not null , email text not null , password text not null); ";


    private static final String TABLE_NAME1 = "money_manager";
    private static final String COULMN_ID1 = "id";
    private static final String COULMN_EMAIL1 = "email";
    private static final String COULMN_DATE1 = "date";
    private static final String COLUMN_ITEM_BOUGHT1 = "item_bought";
    private static final String COULMN_AMMOUNT_RECEIVED1 = "ammount_received";
    private static final String COULMN_AMMOUNT_SPENT1 = "ammount_spent";
    private static final String COLUMN_EMAIL_COUNT1 = "email_count";

    private static final String TABLE_CREATE1 = "create table " + TABLE_NAME1 + " (id integer primary key not null  , " +
            "email text not null , date text not null , item_bought text not null ,ammount_received text not null, ammount_spent text not null); ";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE1);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        String query1 = "DROP TABLE IF EXISTS " + TABLE_NAME1;

        db.execSQL(query);
        db.execSQL(query1);

        this.onCreate(db);
    }

    public String searchPassword(String Email) {

        db = getReadableDatabase();
        String query = "select email,password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String password = "Not found";
        String email;
        if (cursor.moveToFirst()) {

            do {
                email = cursor.getString(0);
                if (email.equals(Email)) {
                    password = cursor.getString(1);
                    break;
                }

            } while (cursor.moveToNext());
        }
        return password;
    }

    public void insertContact(Contact contact) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COULMN_ID, count);
        values.put(COULMN_NAME, contact.getName());
        values.put(COULMN_EMAIL, contact.getEmail());
        values.put(COULMN_PASSWORD, contact.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void insertEntry(Entry entry) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from " + TABLE_NAME1;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COULMN_ID1, count);
        values.put(COULMN_EMAIL1, entry.getEmail());
        values.put(COULMN_DATE1, entry.getDate());
        values.put(COLUMN_ITEM_BOUGHT1, entry.getItem_bought());
        values.put(COULMN_AMMOUNT_RECEIVED1, entry.getAmmount_received());
        values.put(COULMN_AMMOUNT_SPENT1, entry.getAmmount_spent());


        try{
            db.insert(TABLE_NAME1, null, values);

        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        db.close();
        Toast.makeText(context, "New entry made.", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<String> getEntries() {

        SessionManagement sessionManagement = new SessionManagement(context);
        String email = sessionManagement.getSession();

        ArrayList<String> entry = new ArrayList<String>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME1 + " where email=?", new String[]{email}, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String item = cursor.getString(cursor.getColumnIndex("item_bought"));
                String ammount_received = cursor.getString(cursor.getColumnIndex("ammount_received"));
                String ammount_spent = cursor.getString(cursor.getColumnIndex("ammount_spent"));
                entry.add(date+" "+item+" "+ammount_received+" "+ammount_spent);

                cursor.moveToNext();
            }
        }

        return entry;
    }

    public String getPreviousEntry() {

        String ammount = "0 0";
        String email;
        SessionManagement sessionManagement = new SessionManagement(context);
        email = sessionManagement.getSession();

        db = this.getWritableDatabase();

        Cursor count_cursor = db.rawQuery("select * from " + TABLE_NAME1 + " where email=?", new String[]{email}, null);
        int count = count_cursor.getCount();
        int index = count - 1;

        db = getReadableDatabase();
        if (index != -1) {
            Cursor cursor = db.rawQuery("select * from " + TABLE_NAME1 + " where email=? ORDER BY id DESC LIMIT 1", new String[]{email}, null);

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String ammount_received = cursor.getString(cursor.getColumnIndex("ammount_received"));
                    String ammount_spent = cursor.getString(cursor.getColumnIndex("ammount_spent"));

                    ammount = ammount_received + " " + ammount_spent;
                    cursor.moveToNext();
                }
            }
        }

        return ammount;
    }

    public void deleteEntries(){
        db = this.getWritableDatabase();
        SessionManagement sessionManagement = new SessionManagement(context);
        String email = sessionManagement.getSession();
        db.execSQL("DELETE FROM " + TABLE_NAME1 + " where email=?", new String[]{email});
    }

    public void changeEntries(String date, String purpose, String income, String expenditure){
        String query = "UPDATE "+TABLE_NAME1+" SET ammount_received = ?, ammount_spent = ? WHERE date=? and email=? and item_bought=? and ammount_received=? and ammount_spent=?";
        db = this.getWritableDatabase();
        SessionManagement sessionManagement = new SessionManagement(context);
        String email = sessionManagement.getSession();
        if(income.equals("0")){
            db.execSQL(query,new String[]{"0",changeExpense.changeExpense,date,email,purpose,income,expenditure});
        }

        else if(expenditure.equals("0")){
            db.execSQL(query,new String[]{changeIncome.changeIncome,"0",date,email,purpose,income,expenditure});
        }
    }

    public void deleteFromList(String date, String purpose){
        db = this.getWritableDatabase();
        SessionManagement sessionManagement = new SessionManagement(context);
        String email = sessionManagement.getSession();
        db.execSQL("DELETE FROM " + TABLE_NAME1 + " where email=? and date=? and item_bought=?", new String[]{email,date,purpose});
    }
}
