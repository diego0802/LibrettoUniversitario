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

public class RegistrazioneActivity extends AppCompatActivity {
    public static final String PERSON_PATH = "com.example.esercitazione2v0.person";

    protected EditText name, surname, birthdate, password;
    protected Button registration;
    protected Persona person;
    protected Calendar calendar;
    protected SimpleDateFormat simpleDateFormat;
    protected Intent result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        birthdate = findViewById(R.id.birthdate);
        password = findViewById(R.id.password);


        registration = findViewById(R.id.insert);

        person = new Persona();
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);


        registration.setOnClickListener(v -> {
            if (checkInput()) { // If the inputs are valid
                // Debug output to check the flow
                Log.d("RegistrazioneActivity", "Insert button clicked and inputs are valid.");

                // Create an intent to switch from MainActivity to ResultActivity
                result = new Intent(RegistrazioneActivity.this, LoginActivity.class);
                updatePerson();
                result.putExtra(PERSON_PATH, person); // Pass data to the intent
                startActivity(result); // Launch the intent
            } else {
                // Debug output to check if the checkInput function has errors
                Log.d("RegistrazioneActivity", "Insert button clicked, but inputs are not valid.");
            }
        });


        // Mostra il DatePickerFragment in caso di focus attivo
        birthdate.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDialog();
        });

        // Mostra il DatePickerFragment in caso di click
        birthdate.setOnClickListener(v -> showDialog());
    }

    // Funzione che mostra il DatePickerFragment tramite il DialogFragment
    protected void showDialog() {
        DialogFragment dialogFragment = DatePickerFragment.newInstance();
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    // Questa funzione viene eseguita in caso venga premuto il tasto per confermare
    protected void doPositiveClick(Calendar calendar) {
        birthdate.setText(simpleDateFormat.format(calendar.getTime()));
        birthdate.setError(null);
    }

    // Questa funzione viene eseguita in caso venga premuto il tasto per annullare
    protected void doNegativeClick() {
    }

    protected void updatePerson() {
        person.setNome(name.getText().toString());
        person.setCognome(surname.getText().toString());

        try {
            calendar.setTime(Objects.requireNonNull(simpleDateFormat.parse(birthdate.getText().toString())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        person.setDataNascita(calendar);
        person.setPassword(password.getText().toString());
    }


        protected boolean checkInput() {
            int errors = 0;

            if (name.getText() == null || name.getText().length() == 0) {
                errors++;
                name.setError("Inserire il nome");
            } else {
                // Verifica che il nome contenga solo lettere
                if (!name.getText().toString().matches("[a-zA-Z\\s]+")) {
                    errors++;
                    name.setError("Il nome deve contenere solo lettere");
                } else {
                    name.setError(null);
                }
            }

            if (surname.getText() == null || surname.getText().length() == 0) {
                errors++;
                surname.setError("Inserire il cognome");
            } else {
                // Verifica che il nome contenga solo lettere
                if (!surname.getText().toString().matches("[a-zA-Z\\s]+")) {
                    errors++;
                    surname.setError("Il cognome deve contenere solo lettere");
                } else {
                    surname.setError(null);
                }
            }

            if (birthdate.getText() == null || birthdate.getText().length() == 0) {
                errors++;
                birthdate.setError("Inserire la data");
            } else {
                birthdate.setError(null);

                // Verifica che l'anno sia maggiore o uguale a 2004
                String dateString = birthdate.getText().toString();
                int year = Integer.parseInt(dateString.substring(dateString.length() - 4));

                if (year > 2004) {
                    errors++;
                    birthdate.setError("L'anno deve essere maggiore o uguale a 2004");
                } else {
                    birthdate.setError(null);
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
