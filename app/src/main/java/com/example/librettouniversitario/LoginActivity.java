package com.example.librettouniversitario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    public static final String PERSON_PATH = "com.example.esercitazione2v0.person";

    protected EditText name, password;
    protected Button login;
    protected Persona person;
    protected Intent result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);


        login = findViewById(R.id.insert);

        person = new Persona();


        login.setOnClickListener(v -> {
            if (checkInput()) { // If the inputs are valid
                // Debug output to check the flow
                Log.d("LoginActivity", "Insert button clicked and inputs are valid.");

                // Create an intent to switch from MainActivity to ResultActivity
                result = new Intent(LoginActivity.this, EsamiActivity.class);
                updatePerson();
                result.putExtra(PERSON_PATH, person); // Pass data to the intent
                startActivity(result); // Launch the intent
            } else {
                // Debug output to check if the checkInput function has errors
                Log.d("LoginActivity", "Insert button clicked, but inputs are not valid.");
            }
        });


    }

    protected void updatePerson() {
        person.setNome(name.getText().toString());
        person.setPassword(password.getText().toString());
    }


    protected boolean checkInput() {
        int errors = 0;

        if (name.getText() == null || name.getText().length() == 0) {
            errors++;
            name.setError("Inserire il nome: ");
        } else {
            // Verifica che il nome contenga solo lettere
            if (!name.getText().toString().matches("[a-zA-Z\\s]+")) {
                errors++;
                name.setError("Il nome deve contenere solo lettere");
            } else {
                name.setError(null);
            }
        }

        if (password.getText() == null || password.getText().length() == 0) {
            errors++;
            password.setError("Inserire la password");
        } else
            password.setError(null);

        return errors == 0; // True se non vi sono errori, altrimenti false
    }
}

