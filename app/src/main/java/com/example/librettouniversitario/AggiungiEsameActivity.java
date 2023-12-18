package com.example.librettouniversitario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class AggiungiEsameActivity extends AppCompatActivity {
    public static final String EXAM_PATH = "com.example.librettouniversitario.exam";

    protected EditText nomeEsame;
    // Dichiarazione di una variabile di classe per tenere traccia dei nomi degli esami
    private Set<String> nomiEsamiInseriti = new HashSet<>();
    protected EditText numeroCFU, voto;
    protected Button addExam;
    protected Esame exam;
    protected Intent result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aggiungi_esame_activity);

        nomeEsame = findViewById(R.id.name_exam);
        numeroCFU = findViewById(R.id.cfunumber);
        voto = findViewById(R.id.votoid);

        addExam = findViewById(R.id.add);

        exam = new Esame();

        addExam.setOnClickListener(v -> {
            if (checkInput()) {
                Log.d("AggiungiEsameActivity", "Insert button clicked and inputs are valid.");

                updateExam();

                // Creazione dell'Intent per il risultato
                Intent resultIntent = new Intent();

                // Passaggio dell'oggetto Esame come extra
                resultIntent.putExtra(EXAM_PATH, exam);

                // Impostazione del risultato come RESULT_OK e passaggio dell'Intent
                setResult(RESULT_OK, resultIntent);

                // Chiusura dell'Activity corrente
                finish();
            } else {
                Log.d("AggiungiEsameActivity", "Insert button clicked, but inputs are not valid.");
            }
        });

    }

    protected void updateExam() {
        exam.setNomeEsame(nomeEsame.getText().toString());
        exam.setNumeroCFU(Integer.parseInt(numeroCFU.getText().toString()));
        exam.setVoto(Integer.parseInt(voto.getText().toString()));
    }

    protected boolean checkInput() {
        int errors = 0;

        if (nomeEsame.getText() == null || nomeEsame.getText().length() == 0) {
            errors++;
            nomeEsame.setError("Inserire il nome dell'esame");
        } else {
            // Verifica che il nome contenga solo lettere
            if (!nomeEsame.getText().toString().matches("[a-zA-Z0-9\\s]+")) {
                errors++;
                nomeEsame.setError("Il nome può contenere solo lettere, numeri e spazi");
            } else {
                // Verifica che non ci siano esami con lo stesso nome
                String nomeEsameText = nomeEsame.getText().toString();
                if (nomiEsamiInseriti.contains(nomeEsameText)) {
                    errors++;
                    nomeEsame.setError("Esame con lo stesso nome già inserito");
                } else {
                    // Aggiungi il nome dell'esame alla lista
                    nomiEsamiInseriti.add(nomeEsameText);

                    // Se tutte le verifiche sono passate, resetta l'errore
                    nomeEsame.setError(null);
                }
            }
        }

        if (numeroCFU.getText() == null || numeroCFU.getText().length() == 0) {
            errors++;
            numeroCFU.setError("Inserire il numero di CFU");
        } else {
            // Verifica se il numeroCFU è un intero
            try {
                Integer.parseInt(numeroCFU.getText().toString());
                numeroCFU.setError(null);
            } catch (NumberFormatException e) {
                errors++;
                numeroCFU.setError("Inserire un valore numerico intero per i CFU");
            }
        }

        if (voto.getText() == null || voto.getText().length() == 0) {
            errors++;
            voto.setError("Inserire il voto");
        } else {
            // Verifica se il voto è un intero compreso tra 18 e 31
            try {
                int votoValue = Integer.parseInt(voto.getText().toString());
                if (votoValue < 18 || votoValue > 31) {
                    throw new NumberFormatException();
                }
                voto.setError(null);
            } catch (NumberFormatException e) {
                errors++;
                voto.setError("Inserire un voto numerico intero compreso tra 18 e 31 (31 = 30L)");
            }
        }

        return errors == 0;
    }

}
