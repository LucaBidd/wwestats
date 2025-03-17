package com.luca.wwestatsspring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.luca.wwestatsspring.model.Evento;
import com.luca.wwestatsspring.repository.EventoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventoService {

    EventoRepository repository;

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
}
