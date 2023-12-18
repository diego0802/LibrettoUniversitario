package com.example.librettouniversitario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EsamiAdapter extends RecyclerView.Adapter<EsamiAdapter.EsameViewHolder> {

    private final List<Esame> listaEsami;

    public EsamiAdapter(List<Esame> listaEsami) {
        this.listaEsami = listaEsami;
    }

    @NonNull
    @Override
    public EsameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_esame, parent, false);
        return new EsameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EsameViewHolder holder, int position) {
        Esame esame = listaEsami.get(position);
        holder.nomeEsameTextView.setText("Nome Corso: " + esame.getNomeEsame());
        holder.numeroCFUTextView.setText("Numero CFU: " + String.valueOf(esame.getNumeroCFU()));
        // Verifica se il voto è uguale a 31
        if (esame.getVoto() == 31) {
            holder.votoTextView.setText("Voto: 30L");
        } else {
            holder.votoTextView.setText("Voto: " + esame.getVoto());
        }
    }

    @Override
    public int getItemCount() {
        return listaEsami.size();
    }

    public void aggiungiEsame(Esame esame) {
        listaEsami.add(esame);
        notifyItemInserted(listaEsami.size() - 1);
    }

    public static class EsameViewHolder extends RecyclerView.ViewHolder {

        public TextView nomeEsameTextView;
        public TextView numeroCFUTextView;
        public TextView votoTextView;

        public EsameViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeEsameTextView = itemView.findViewById(R.id.nomeEsameTextView);
            numeroCFUTextView = itemView.findViewById(R.id.numeroCFUTextView);
            votoTextView = itemView.findViewById(R.id.votoTextView);
        }
    }
}
