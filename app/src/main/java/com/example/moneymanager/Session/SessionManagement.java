package com.example.moneymanager.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "Session";
    String SESSION_KEY = "session_user";


    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(String email){
        //save session of user whenever user is logged in.
        editor.putString(SESSION_KEY,email).commit();

    }

    public String getSession(){
        //return user whose session is saved.
        return sharedPreferences.getString(SESSION_KEY,"");
    }

    public void removeSession(){
        editor.putString(SESSION_KEY,"").commit();
    }
}
