package com.example.librettouniversitario;

import java.io.Serializable;

public class Esame implements Serializable {

    private String nomeEsame;
    private int numeroCFU;
    private int voto;

    // Costruttore
    public Esame() {

    }

    public Esame(String nomeEsame, int numeroCFU, int voto) {
        this.nomeEsame = nomeEsame;
        this.numeroCFU = numeroCFU;
        this.voto = voto;
    }


    // Metodi getter e setter per ciascun campo
    public String getNomeEsame() {
        return nomeEsame;
    }

    public void setNomeEsame(String nomeEsame) {
        this.nomeEsame = nomeEsame;
    }

    public int getNumeroCFU() {
        return numeroCFU;
    }

    public void setNumeroCFU(int numeroCFU) {
        this.numeroCFU = numeroCFU;
    }

    public double getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    @Override
    public String toString() {
        return "Esame{" +
                "nomeEsame='" + nomeEsame + '\'' +
                ", numeroCFU=" + numeroCFU +
                ", voto=" + voto +
                '}';
    }
}
