package com.example.librettouniversitario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EsamiActivity extends AppCompatActivity {

    private List<Esame> listaEsami;
    private EsamiAdapter esamiAdapter;
    private TextView textViewMediaAritmetica, textViewMediaPonderata, textViewVotoLaurea;

    // Dichiarazione dell'ActivityResultLauncher
    private final ActivityResultLauncher<Intent> aggiungiEsameLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Esame nuovoEsame = (Esame) data.getSerializableExtra(AggiungiEsameActivity.EXAM_PATH);
                        if (nuovoEsame != null) {
                            esamiAdapter.aggiungiEsame(nuovoEsame);
                            updateCalculatedValues();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.esami_activity);

        RecyclerView recyclerViewEsami = findViewById(R.id.recyclerViewEsami);
        Button btnAggiungiEsame = findViewById(R.id.btnAggiungiEsame);

        textViewMediaAritmetica = findViewById(R.id.textViewMediaAritmetica);
        textViewMediaPonderata = findViewById(R.id.textViewMediaPonderata);
        textViewVotoLaurea = findViewById(R.id.textViewVotoLaurea);

        listaEsami = new ArrayList<>();
        esamiAdapter = new EsamiAdapter(listaEsami);

        recyclerViewEsami.setAdapter(esamiAdapter);
        recyclerViewEsami.setLayoutManager(new LinearLayoutManager(this));

        btnAggiungiEsame.setOnClickListener(v -> {
            Intent intent = new Intent(EsamiActivity.this, AggiungiEsameActivity.class);
            aggiungiEsameLauncher.launch(intent);
        });
    }

    // Metodo per aggiornare i valori calcolati e visualizzarli nelle TextView
    private void updateCalculatedValues() {
        double sommaVoti = 0;
        int sommaCFU = 0;
        double somma = 0;

        for (Esame esame : listaEsami) {
            sommaVoti += esame.getVoto() * esame.getNumeroCFU();
            sommaCFU += esame.getNumeroCFU();
            somma+=esame.getVoto();
        }

        double mediaAritmetica = (sommaCFU == 0) ? 0 : somma / listaEsami.size();

        double sommaProdottiVotiCFU = 0;
        for (Esame esame : listaEsami) {
            sommaProdottiVotiCFU += esame.getVoto() * esame.getNumeroCFU();
        }
        double mediaPonderata = (sommaCFU == 0) ? 0 : sommaProdottiVotiCFU / sommaCFU;

        double votoLaurea = (sommaCFU == 0) ? 0 : mediaPonderata * 110 / 30;

        DecimalFormat decimalFormat = new DecimalFormat("#.###");

        textViewMediaAritmetica.setText("Media Aritmetica: " + decimalFormat.format(mediaAritmetica));
        textViewMediaPonderata.setText("Media Ponderata: " + decimalFormat.format(mediaPonderata));
        textViewVotoLaurea.setText("Voto di Laurea: " + decimalFormat.format(votoLaurea));
    }



}
