package com.luca.wwestatsspring.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.luca.wwestatsspring.model.Wrestler;

@Repository
public interface WrestlerRepository extends MongoRepository<Wrestler, String> {

    // Trova in base al nome
    Optional<Wrestler> findWrestlerByNome(String nome);

    // Cancella in base al nome
    void deleteByNome(String nome);

    // Trova in base alla nazionalità
    List<Wrestler> findByNazionalita(String nazionalita);

    // Trova se l'altezza è tra due valori
    List<Wrestler> findByAltezzaBetween(double min, double max);

    // Trova il wrestler con la maggiore altezza
    Optional<Wrestler> findTopByOrderByAltezzaDesc();

    // Trova in base al nome ignorando maiuscole/minuscole
    List<Wrestler> findByNomeContainingIgnoreCase(String nome);

    // Aggiunta: Trova wrestler per peso
    List<Wrestler> findByPesoBetween(double minPeso, double maxPeso);

    // Aggiunta: Trova il wrestler più pesante
    Optional<Wrestler> findTopByOrderByPesoDesc();

    // Aggiunta: Trova wrestler con un determinato debutto
    List<Wrestler> findByDebutto(LocalDate debutto);

    // Aggiunta: Trova wrestler con una determinata categoria di peso
    List<Wrestler> findByCategoria(String categoria);
}
