package com.luca.wwestatsspring.controller;

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

import com.luca.wwestatsspring.model.Match;
import com.luca.wwestatsspring.model.Stipulazione;
import com.luca.wwestatsspring.model.TipoMatch;
import com.luca.wwestatsspring.model.Wrestler;
import com.luca.wwestatsspring.service.MatchService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/matches")
@AllArgsConstructor
public class MatchController {

    private final MatchService matchService;

    /*** CRUD BASE ***/
    
    @GetMapping
    public List<Match> getAll() {
        return matchService.getAllMatches();
    }
    
    @GetMapping("/{id}")
    public Optional<Match> getMatch(@PathVariable String id) {        
        return matchService.getById(id);
    }

    @PostMapping
    public Match addMatch(@RequestBody Match m) {
        return matchService.addMatch(m);
    }
    
    @PutMapping("/{id}")
    public Match updateMatch(@PathVariable String id, @RequestBody Match m) {
        return matchService.updateMatch(id, m);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable String id) {
        matchService.deleteById(id);
    }

    /*** Altre operazioni ***/

    @GetMapping("/filter/type/{tipo}")
    public List<Match> filterByTipo(@PathVariable TipoMatch tipo) {
        return matchService.getByTipo(tipo);
    }

    @GetMapping("/filter/stipulation/{stipulazione}")
    public List<Match> filterByStipulazione(@PathVariable Stipulazione stipulazione) {
        return matchService.getByStipulazione(stipulazione);
    }
    
    @GetMapping("/filter/{partecipanti}")
    public List<Match> filterByPartecipanti(@PathVariable("partecipanti") String nomePartecipante) {
        return matchService.getByNomePartecipanti(nomePartecipante);
    }

    @GetMapping("/filter/win/{vincitori}")
    public List<Match> filterByVincitori(@PathVariable("vincitori") String nomeVincitore) {
        return matchService.getByNomeVincitori(nomeVincitore);
    }

    @GetMapping("/filter/shortest")
    public Optional<Match> filterShortest() {
        return matchService.getShortest();
    }

    @GetMapping("/filter/longest")
    public Optional<Match> filterLongest() {
        return matchService.getLongest();
    }

    @GetMapping("/{id}/wrestlers")
    public List<Wrestler> getWrestlersByMatch(@PathVariable String id) {
        return matchService.getWrestlersByMatch(id);
    }

}
