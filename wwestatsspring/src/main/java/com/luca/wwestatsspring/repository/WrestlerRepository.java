package com.luca.wwestatsspring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.luca.wwestatsspring.model.Wrestler;

@Repository
public interface WrestlerRepository extends MongoRepository<Wrestler, String>{
    
    //Trova in base al nome
    Optional<Wrestler> findWrestlerByNome(String nome);

    //Cancella in base al nome
    void deleteByNome(String nome);

    //Trova in base alla nazionalità
    List<Wrestler> findByNazionalita(String nazionalita);

    //Trova in se l'altezza è tra due valori
    List<Wrestler> findByAltezzaBetween(int min, int max);

    //Trova il primo In ordine di altezza
    Optional<Wrestler> findTopByOrderByAltezzaDesc();

    //Trova in base al nome senza considerare maiusc/minusc
    List<Wrestler> findByNomeContainingIgnoreCase(String nome);

}
