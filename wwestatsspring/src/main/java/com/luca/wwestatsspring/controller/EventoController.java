package com.luca.wwestatsspring.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.wwestatsspring.model.Evento;
import com.luca.wwestatsspring.service.EventoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/matches")
@AllArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    /*** CRUD BASE ***/
    
    @GetMapping
    public List<Evento> getAll() {
        return eventoService.getAllEventi();
    }
    
    @GetMapping("/{id}")
    public Optional<Evento> getEvento(@PathVariable String id) {        
        return eventoService.getById(id);
    }

    @PostMapping
    public Evento addMatch(@RequestBody Evento e) {
        return eventoService.addEvento(e);
    }
    
    @PutMapping("/{id}")
    public Evento updateEvento(@PathVariable String id, @RequestBody Evento e) {
        return eventoService.updatEvento(id, e);
    }

    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable String id) {
        eventoService.deleteById(id);
    }

    /*** Altre operazioni ***/


    //Trova tutti gli eventi con quel nome
    public List<Evento> findByNome(String nome){
        return eventoService.findByNome(nome);
    }

    //Trova in base al nome senza considerare maiusc/minusc
    public List<Evento> findByNomeSimile(String nome){
        return eventoService.findByNomeSimile(nome);
    }

    //Trova in base alla data
    public List<Evento> filtraByData(LocalDate data){
        return eventoService.findByData(data);
    }

    //Trova in base allo stato
    public List<Evento> filtraByStato(String stato){
        return eventoService.findByStato(stato);
    }

    //Trova in base alla citta
    public List<Evento> filtrByCitta(String citta){
        return eventoService.findByCitta(citta);
    }
}
