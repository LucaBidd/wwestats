package com.luca.wwestatsspring.model;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data       // getter, setter, toString, equals e hashCode
@Document   // la classe è un documento di MongoDB
public class Match {

    private TipoMatch tipo;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime durata;

    private Stipulazione stipulazione;
    
    private List<String> partecipanti;  //lista di nomi di wrestler
    
    private List<String> vincitori;   //lista di nomi di wrestler vincitori

    private String eventoId;

}
