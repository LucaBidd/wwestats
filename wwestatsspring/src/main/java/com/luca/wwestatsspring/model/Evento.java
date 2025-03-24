package com.luca.wwestatsspring.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
@Document
public class Evento {

    @Id
    private String eventoId;

    private String nome;
    
    private LocalDate data;
    
    private String stato;
    
    private String citta;
    
    private List<String> matchesIds;  // Lista di ID dei match che appartengono a questo evento

}
