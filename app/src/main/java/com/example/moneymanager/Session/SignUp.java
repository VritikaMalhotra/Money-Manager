package com.example.moneymanager.Session;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.DBConnection.Contact;
import com.example.moneymanager.DBConnection.DBHelper;
import com.example.moneymanager.R;

public class SignUp extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);

    private EditText editTextinputFullName;
    private EditText editTextinputPassword;
    private EditText editTextinputConfirmPassword;
    private EditText editTextinputEmail;

    private Button buttonSignUp;
    private TextView textViewSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextinputFullName = findViewById(R.id.inputFullName);
        editTextinputEmail = findViewById(R.id.inputEmail);
        editTextinputPassword = findViewById(R.id.inputPassword);
        editTextinputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        buttonSignUp = findViewById(R.id.buttonsSignUp);
        textViewSignIn = findViewById(R.id.textSignIn);

        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignIn.class));
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextinputFullName.getText().toString().trim().isEmpty()) {

                    editTextinputFullName.setError("Full name required");
                    editTextinputFullName.requestFocus();
                    return;
                }
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
                if (editTextinputConfirmPassword.getText().toString().trim().isEmpty()) {
                    editTextinputConfirmPassword.setError("Confirm Password is required");
                    editTextinputConfirmPassword.requestFocus();
                    return;
                }
                if (!((editTextinputPassword.getText().toString().trim()).equals(editTextinputConfirmPassword.getText().toString().trim()))) {

                    editTextinputConfirmPassword.setError("Confirm Password should match you previously entered password");
                    editTextinputConfirmPassword.requestFocus();
                    return;
                }
                signup();
            }
        });

    }

    public void signup(){

        Contact contact = new Contact();
        contact.setName(editTextinputFullName.getText().toString());
        contact.setEmail(editTextinputEmail.getText().toString());
        contact.setPassword(editTextinputPassword.getText().toString());

        helper.insertContact(contact);
        Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), SignIn.class));
    }

}