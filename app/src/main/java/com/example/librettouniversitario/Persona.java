package com.example.librettouniversitario;

import java.io.Serializable;
import java.util.Calendar;

public class Persona implements Serializable {

    private String nome;
    private String cognome;
    private Calendar dataNascita;
    private String password;
    // Aggiungi altri campi se necessario

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Calendar getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Calendar dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Aggiungi getter e setter per altri campi se necessario

    @Override
    public String toString() {
        return "Persona{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita='" + dataNascita + '\'' +
                ", password='" + password + '\'' +
                // Aggiungi altri campi se necessario
                '}';
    }
}

