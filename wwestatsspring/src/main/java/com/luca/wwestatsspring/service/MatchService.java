package com.luca.wwestatsspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.luca.wwestatsspring.model.Match;
import com.luca.wwestatsspring.model.Stipulazione;
import com.luca.wwestatsspring.model.TipoMatch;
import com.luca.wwestatsspring.repository.MatchRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepository repository;
//---crud base---
    public List<Match> getAllMatches() {
        return repository.findAll();
    }

    public Optional<Match> getById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
        System.out.println("Match " + id + " eliminato con successo");
    }
    
    public Match addMatch(Match match){
        return repository.save(match);
    }
    
    public Match updateMatch(String id, Match match) {
        return repository.findById(id)
                .map(existingMatch -> {
                    existingMatch.setDurata(match.getDurata());
                    existingMatch.setTipo(match.getTipo());
                    existingMatch.setStipulazione(match.getStipulazione());
                    existingMatch.setPartecipanti(match.getPartecipanti());
                    existingMatch.setVincitori(match.getVincitori());
                    return repository.save(existingMatch);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match con ID " + id + " non trovato"));
    }
    
//----altro---
    public List<Match> getByTipo(TipoMatch tipo){
        return repository.findByTipo(tipo);
    }

    public List<Match> getByStipulazione(Stipulazione stipulazione){
        return repository.findByStipulazione(stipulazione);
    }

    public List<Match> getByNomePartecipanti(String nome){
        return repository.findByPartecipantiContaining(nome);
    }

    public List<Match> getByNomeVincitori(String nome){
        return repository.findByVincitoriContaining(nome);
    }

    public Optional<Match> getShortest(){
        return repository.findTopByOrderByDurataAsc();
    }

    public Optional<Match> getLongest(){
        return repository.findTopByOrderByDurataDesc();
    }

    public String getWrestlerStats(String nome){
        int matchDisputati = getByNomePartecipanti(nome).size();
        int vittorie = getByNomeVincitori(nome).size();
        int sconfitte = matchDisputati - vittorie;

        String result = ("Match disputati: " + matchDisputati + "\n" +
                        "Vittorie: " + vittorie + "\n" +
                        "Sconfitte: " + sconfitte);
        return result; 
    }

    public long countMatchesByWrestlerId(String id) {
        return repository.findByPartecipantiContaining(id).size();
    }
    
    public long countWinsByWrestlerId(String id) {
        return repository.findByVincitoriContaining(id).size();
    }
    

    public List<Match> getByEventId(String eventoId){
        return repository.findByEventoId(eventoId);
    }
}
