package com.luca.wwestatsspring.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.luca.wwestatsspring.model.Evento;
import com.luca.wwestatsspring.model.Match;
import com.luca.wwestatsspring.model.Wrestler;
import com.luca.wwestatsspring.service.EventoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/events")
@AllArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    /*** CRUD BASE ***/
    
    @GetMapping
    public List<Evento> getAll() {
        return eventoService.getAllEventi();
    }
    
    @GetMapping("/{id}")
    public Evento getEvento(@PathVariable String id) {
        return eventoService.getById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento con ID " + id + " non trovato"));
    }

    @PostMapping
    public Evento addEvento(@RequestBody Evento evento) {
        return eventoService.addEvento(evento);
    }
    
    @PutMapping("/{id}")
    public Evento updateEvento(@PathVariable String id, @RequestBody Evento evento) {
        return eventoService.updatEvento(id, evento);
    }

    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable String id) {
        eventoService.deleteById(id);
    }

    /*** Altre operazioni ***/

    @GetMapping("/filter/{nome}")
    public List<Evento> findByNome(@PathVariable String nome) {
        return eventoService.findByNome(nome);
    }

    @GetMapping("/search/{nome}")
    public List<Evento> findByNomeSimile(@PathVariable String nome) {
        return eventoService.findByNomeSimile(nome);
    }

    @GetMapping("/filter/date/{data}")
    public List<Evento> filtraByData(@PathVariable LocalDate data) {
        return eventoService.findByData(data);
    }

    @GetMapping("/filter/country/{stato}")
    public List<Evento> filtraByStato(@PathVariable String stato) {
        return eventoService.findByStato(stato);
    }

    @GetMapping("/filter/city/{citta}")
    public List<Evento> filtraByCitta(@PathVariable String citta) {
        return eventoService.findByCitta(citta);
    }

    @GetMapping("/count/country/{stato}")
    public long countByStato(@PathVariable String stato) {
        return eventoService.countByStato(stato);
    }

    @GetMapping("/count/city/{citta}")
    public long countByCitta(@PathVariable String citta) {
        return eventoService.countByCitta(citta);
    }

    @GetMapping("/{id}/matches")
    public List<Match> getMatchesByEvento(@PathVariable String id) {
        return eventoService.getMatchesByEvento(id);
    }

    @GetMapping("/{id}/wrestlers")
    public List<Wrestler> getWrestlersByEvento(@PathVariable String id) {
        return eventoService.getWrestlersByEvento(id);
    }

}
