package com.luca.wwestatsspring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.luca.wwestatsspring.model.Match;
import com.luca.wwestatsspring.model.Stipulazione;
import com.luca.wwestatsspring.model.TipoMatch;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends MongoRepository<Match, String> {

    // Trova in base al tipo di match
    List<Match> findByTipo(TipoMatch tipo);

    // Trova in base alla stipulazione
    List<Match> findByStipulazione(Stipulazione stipulazione);

    // Trova i match in cui ha partecipato un wrestler
    List<Match> findByPartecipantiContaining(String nome);

    // Trova i match che ha vinto un wrestler
    List<Match> findByVincitoriContaining(String nome);

    // Trova il match più breve
    Optional<Match> findTopByOrderByDurataAsc();

    // Trova il match più lungo
    Optional<Match> findTopByOrderByDurataDesc();

    // Trova i match in un evento specifico
    List<Match> findByEventoId(String eventoId);


}
