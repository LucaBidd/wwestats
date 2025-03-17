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

    public List<Match> getAllMatches() {
        return repository.findAll();
    }

    public Optional<Match> getById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id){
        // Verifica se esiste un match con quel nome
        Optional<Match> w = repository.findById(id);
        if (w.isPresent()) {
            repository.deleteById(id);
            System.out.println("Match " + id +" eliminato con successo");
        } else {
            System.out.println("Match " + id +" non trovato");
        }
    }
    
    public Match addMatch(Match match){
        return repository.save(match);
    }
    
    public Match updateMatch(String id, Match m) {
        return repository.findById(id).map(existingMatch -> {
            existingMatch.setDurata(m.getDurata());
            existingMatch.setTipo(m.getTipo());
            existingMatch.setStipulazione(m.getStipulazione());
            existingMatch.setPartecipanti(m.getPartecipanti());
            existingMatch.setVincitori(m.getVincitori());

            System.out.println("Match modificato correttamente");
            return repository.save(existingMatch); // Usare un repository coerente
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match con ID " + id + " non trovato"));
    }

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

}
