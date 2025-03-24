package com.luca.wwestatsspring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.luca.wwestatsspring.model.Evento;
import com.luca.wwestatsspring.model.Match;

@Repository
public interface EventoRepository  extends MongoRepository<Evento, String>{

    // Trova tutti gli eventi con quel nome
    List<Evento> findByNome(String nome);

    // Trova in base al nome senza considerare maiusc/minusc
    List<Evento> findByNomeContainingIgnoreCase(String nome);

    // Trova in base alla data
    List<Evento> findByData(LocalDate data);

    // Trova eventi prima di una certa data
    List<Evento> findByDataBefore(LocalDate data);

    // Trova eventi dopo una certa data
    List<Evento> findByDataAfter(LocalDate data);

    // Trova eventi in base allo stato
    List<Evento> findByStato(String stato);

    // Trova eventi in base alla città
    List<Evento> findByCitta(String citta);

    // Conta gli eventi in uno stato
    int countByStato(String stato);

    // Conta gli eventi in una città
    int countByCitta(String citta);

    // Conta gli eventi in uno stato e una città specifici
    int countByStatoAndCitta(String stato, String citta);

    // Trova eventi in un range di date
    List<Evento> findByDataBetween(LocalDate startDate, LocalDate endDate);

    List<Match> findMatchesByEventoId(String id);
}
