package com.example.moneymanager.Session;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.DBConnection.DBHelper;
import com.example.moneymanager.Logic.Display;
import com.example.moneymanager.R;

public class SignIn extends AppCompatActivity {

    private EditText editTextinputEmail;
    private EditText editTextinputPassword;
    private TextView textViewSignUp;
    Button buttonSignIn;

    DBHelper helper = new DBHelper(this);

    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextinputEmail = findViewById(R.id.inputEmail);
        editTextinputPassword = findViewById(R.id.inputPassword);
        textViewSignUp = findViewById(R.id.textSignUp);
        buttonSignIn = findViewById(R.id.buttonsSignIn);

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextinputEmail.getText().toString().trim().isEmpty()) {
                    editTextinputEmail.setError("Email is required");
                    editTextinputEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(editTextinputEmail.getText().toString().trim()).matches()) {
                    editTextinputEmail.setError("Please enter a correct email");
                    editTextinputEmail.requestFocus();
                    return;
                }
                if (editTextinputPassword.getText().toString().trim().isEmpty()) {
                    editTextinputPassword.setError("Password is required");
                    editTextinputPassword.requestFocus();
                    return;
                }
                signIn();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkSession();
    }

    private void checkSession() {
        //Check if user is logged in and if user is logged in directly move to display.
        SessionManagement sessionManagement = new SessionManagement(SignIn.this);
        String userEmail = sessionManagement.getSession();

        if(!(userEmail.isEmpty())) {

            //User is logged in and directly move to display activity.

            Intent intent = new Intent(SignIn.this, Display.class);
            intent.putExtra(userEmail, "Email");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else{
            //do nothing
        }
    }

    public void signIn(){
        String password = helper.searchPassword(editTextinputEmail.getText().toString());
        if(password.equals(editTextinputPassword.getText().toString())){

            SessionManagement sessionManagement = new SessionManagement(SignIn.this);
            sessionManagement.saveSession(editTextinputEmail.getText().toString());

            Intent intent = new Intent(SignIn.this,Display.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }else{

            Toast.makeText(this, "EmailID and Password do not match. Recheck please!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        finish();
    }
}