package com.luca.wwestatsspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.wwestatsspring.model.Wrestler;
import com.luca.wwestatsspring.service.MatchService;
import com.luca.wwestatsspring.service.WrestlerService;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/wrestlers")
@AllArgsConstructor
public class WrestlerController {

    private final MatchService matchService;

    private final WrestlerService wrestlerService;

    /*** CRUD BASE ***/
    
    @GetMapping
    public List<Wrestler> getAll() {
        return wrestlerService.getAllWrestlers();
    }
    
    @GetMapping("/{id}")
    public Wrestler getWrestler(@PathVariable String id) {        
        return wrestlerService.getWrestlerById(id);
    }


    @PostMapping
    public Wrestler addWrestler(@RequestBody Wrestler w) {
        return wrestlerService.addWrestler(w);
    }
    
    @PutMapping("/{id}")
    public Wrestler updateWrestler(@PathVariable String id, @RequestBody Wrestler w) {
        return wrestlerService.updateWrestler(id, w);
    }

    @DeleteMapping("/{id}")
    public void deleteWrestler(@PathVariable String id) {
        wrestlerService.deleteById(id);
    }

    /*** Altre operazioni ***/

    @GetMapping("/filter/{nazionalita}")
    public List<Wrestler> filterByNazionalita(@PathVariable String nazionalita) {
        return wrestlerService.getByNazionalita(nazionalita);
    }

    @GetMapping("/filter/height/{min}/{max}")
    public List<Wrestler> filterByAltezzaBetween(@PathVariable int min,@PathVariable int max) {
        return wrestlerService.getByAltezzaBetween(min, max);
    }
    
    @GetMapping("/filter/tallest")
    public Optional<Wrestler> getTallest() {
        return wrestlerService.getTallest();
    }

    @GetMapping("/filter/weight/{min}/{max}")
    public List<Wrestler> filterByPesoBetween(@PathVariable int min,@PathVariable int max) {
        return wrestlerService.getByPesoBetween(min, max);
    }
    
    @GetMapping("/filter/heaviest")
    public Optional<Wrestler> getHeaviest() {
        return wrestlerService.getHeaviest();
    }

    @GetMapping("/filter/debut/{data}")
    public List<Wrestler> getWrestlerByDebutto(@PathVariable LocalDate debutto) {
        return wrestlerService.getWrestlerByDebutto(debutto);
    }

    @GetMapping("/filter/category/{categoria}")
    public List<Wrestler> getWrestlerByCategoria(@PathVariable String categoria) {
        return wrestlerService.getWrestlerByCategoria(categoria);
    }

    @GetMapping("search/{nome}")
    public List<Wrestler> getWrestlerByNomeSimile(@PathVariable String nome) { 
        return wrestlerService.getWrestlerByNomeSimile(nome);
    }
    
    @GetMapping("/{id}/stats")
    public Map<String, Object> getWrestlerAndStats(@PathVariable String id) {
        Wrestler wrestler = wrestlerService.getWrestlerById(id);
        long matchDisputati = matchService.countMatchesByWrestlerId(id);
        long vittorie = matchService.countWinsByWrestlerId(id);
        long sconfitte = matchDisputati - vittorie;

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("wrestler", wrestler);
        response.put("stats", Map.of(
            "matchDisputati", matchDisputati,
            "vittorie", vittorie,
            "sconfitte", sconfitte
        ));

        return response;
    }

    
}
