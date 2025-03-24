package com.luca.wwestatsspring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.luca.wwestatsspring.model.Evento;

@Repository
public interface EventoRepository  extends MongoRepository<Evento, String>{

    //Trova tutti gli eventi con quel nome
    List<Evento> findByNome(String nome);

    //Trova in base al nome senza considerare maiusc/minusc
    List<Evento> findByNomeContainingIgnoreCase(String nome);

    //Trova in base alla data
    List<Evento> findByData(LocalDate data);

    //Trova in base allo stato
    List<Evento> findByStato(String stato);

    //Trova in base alla citta
    List<Evento> findByCitta(String citta);

    //Conta gli eventi in uno stato
    int countByStato(String stato);

    //Conta gli eventi in una citta
    int countByCitta(String citta);

}
