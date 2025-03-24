package com.luca.wwestatsspring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.luca.wwestatsspring.model.Evento;
import com.luca.wwestatsspring.model.Match;
import com.luca.wwestatsspring.model.Wrestler;
import com.luca.wwestatsspring.repository.EventoRepository;
import com.luca.wwestatsspring.repository.MatchRepository;
import com.luca.wwestatsspring.repository.WrestlerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventoService {

    EventoRepository repository;
    MatchRepository matchRepository;
    WrestlerRepository wrestlerRepository;

    public List<Evento> getAllEventi(){
        return repository.findAll();
    }

    public Optional<Evento> getById(String id){
        return repository.findById(id);
    }

    public Evento addEvento(Evento evento){
        return repository.save(evento);
    }

    public Evento updatEvento(String id, Evento e){
        return repository.findById(id).map(existingEvento -> {
            existingEvento.setNome(e.getNome());
            existingEvento.setData(e.getData());
            existingEvento.setStato(e.getStato());
            existingEvento.setCitta(e.getCitta());
            existingEvento.setMatchesIds(e.getMatchesIds());

            System.out.println("Evento modificato correttamente");
            return repository.save(existingEvento); // Usare un repository coerente
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento con ID " + id + " non trovato"));
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    //Trova tutti gli eventi con quel nome
    public List<Evento> findByNome(String nome){
        return repository.findByNome(nome);
    }

    //Trova in base al nome senza considerare maiusc/minusc
    public List<Evento> findByNomeSimile(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    //Trova in base alla data
    public List<Evento> findByData(LocalDate data){
        return repository.findByData(data);
    }

    //Trova in base allo stato
    public List<Evento> findByStato(String stato){
        return repository.findByStato(stato);
    }

    //Trova in base alla citta
    public List<Evento> findByCitta(String citta){
        return repository.findByCitta(citta);
    }

     //Conta gli eventi in uno stato
    public int countByStato(String stato){
        return repository.countByStato(stato);
    }

     //Conta gli eventi in una citta
    public int countByCitta(String citta){
        return repository.countByCitta(citta);
    }

    //restituisce i meatch dell'evento
    public List<Match> getMatchesByEvento(String eventoId) {
        Evento evento = repository.findById(eventoId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento non trovato"));
        return matchRepository.findAllById(evento.getMatchesIds());
    }

    public List<Wrestler> getWrestlersByEvento(String eventoId) {
        // 1. Troviamo l'evento
        Evento evento = repository.findById(eventoId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento non trovato"));

        // 2. Recuperiamo i match dell'evento
        List<Match> matches = matchRepository.findAllById(evento.getMatchesIds());

        // 3. Estraiamo i nomi unici di tutti i wrestler dai match (partecipanti + vincitori)
        Set<String> wrestlerNomi = matches.stream()
            .flatMap(m -> m.getPartecipanti().stream()) // Prendiamo tutti i partecipanti
            .collect(Collectors.toSet()); // Rimuoviamo eventuali duplicati

        // 4. Recuperiamo i wrestler dai nomi
        List<Wrestler> wrestlers = wrestlerNomi.stream()
            .map(nome -> wrestlerRepository.findWrestlerByNome(nome).orElse(null)) // Cerchiamo nel database
            .filter(w -> w != null) // Rimuoviamo eventuali null (wrestler non trovati)
            .collect(Collectors.toList());

        return wrestlers;
    }

}
