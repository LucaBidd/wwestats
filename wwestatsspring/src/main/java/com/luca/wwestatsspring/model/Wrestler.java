package com.luca.wwestatsspring.model;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data       // getter, setter, toString, equals e hashCode
@Document   // la classe è un documento di MongoDB
public class Wrestler {

    private String id;

    private String nome;
    private LocalDate dataNascita;
    private String nazionalita;
    private double altezza;
    private double peso;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate debutto;

}
