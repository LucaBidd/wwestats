package com.luca.wwestatsspring.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Wrestler {

    @Id
    private String id;

    private String nome;
    private LocalDate dataNascita;
    private String nazionalita;
    private double altezza;
    private double peso;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate debutto;

    private String descrizione; // Campo opzionale per dettagli aggiuntivi

    private String categoria; // Categoria di peso

    // Metodo per assegnare la categoria di peso in base al peso
    public void assegnaCategoriaPeso() {
        if (this.peso <= 93.44) {
            this.categoria = "Cruiserweight ";
        } else if (this.peso <= 118.38) {
            this.categoria = "Light heavyweight";
        } else if (this.peso <= 136.07) {
            this.categoria = "Heavyweight";
        } else {
            this.categoria = "Super heavyweight";
        }
    }

    // Setter per peso con aggiornamento della categoria di peso
    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("Il peso deve essere un valore positivo.");
        }
        this.peso = peso;
        assegnaCategoriaPeso(); // Riassegna la categoria ogni volta che il peso cambia
    }

    // Metodo per convertire il peso da chili a libre
    public double getPesoInLibbre() {
        return this.peso * 2.20462;
    }

    // Metodo per convertire l'altezza da metri a piedi
    public double getAltezzaInPiedi() {
        return this.altezza * 3.28084;
    }

    // Metodo per ottenere una rappresentazione del peso in chili e libre
    public String getPesoFormattato() {
        return String.format("%.2f kg (%.2f lbs)", this.peso, getPesoInLibbre());
    }

    // Metodo per ottenere una rappresentazione dell'altezza in metri e piedi
    public String getAltezzaFormattata() {
        return String.format("%.2f m (%.2f ft)", this.altezza, getAltezzaInPiedi());
    }
}
