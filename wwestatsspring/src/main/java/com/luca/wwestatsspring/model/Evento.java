package com.luca.wwestatsspring.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data       // getter, setter, toString, equals e hashCode
@Document   // la classe è un documento di MongoDB
public class Evento {

    private String nome;
    private LocalDate data;
    private String stato;
    private String citta;

    private List<String> matchesIds;
}
