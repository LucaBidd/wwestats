package com.luca.wwestatsspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.wwestatsspring.model.Wrestler;
import com.luca.wwestatsspring.service.MatchService;
import com.luca.wwestatsspring.service.WrestlerService;

import lombok.AllArgsConstructor;

import java.util.List;
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
    public Optional<Wrestler> getWrestler(@PathVariable String id) {        
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

    @GetMapping("/filter/{min}/{max}")
    public List<Wrestler> filterByAltezzaBetween(@PathVariable int min,@PathVariable int max) {
        return wrestlerService.getByAltezzaBetween(min, max);
    }
    
    @GetMapping("/filter/tallest")
    public Optional<Wrestler> getTallest() {
        return wrestlerService.getTallest();
    }

    @GetMapping("search/{nome}")
    public List<Wrestler> getWrtestlerByNomeSimile(@PathVariable String nome) { 
        return wrestlerService.getWrtestlerByNomeSimile(nome);
    }
    
    @GetMapping("stats/{nome}")
    public String getWrestlerStats(@PathVariable String nome) {
        return matchService.getWrestlerStats(nome);
    }
    
    
}
/*
 * 
 @GetMapping("/stats/{id}")
 public String getMethodName(@RequestParam String param) {
     return new String();
 }
 */


/* 


    /*
    @GetMapping("/{id}")
    public Map<String, Object> getWrestlerAndStats(@PathVariable String id) {
        Wrestler wrestler = wrestlerRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrestler non trovato"));

        long matchDisputati = matchRepository.countMatchDisputati(id);
        long vittorie = matchRepository.countVittorie(id);
        
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("wrestler", wrestler);
        response.put("stats", Map.of(
            "matchDisputati", matchDisputati,
            "vittorie", vittorie,
            "sconfitte", matchDisputati - vittorie
        ));
        
        return response;
    }



    

    
                
                @GetMapping("/nazionalita/{nazionalita}")
    public List<Wrestler> getWrestlersByNazionalita(@PathVariable String nazionalita) {
        return wrestlerRepository.findByNazionalita(nazionalita);
    }

    @GetMapping("/altezza/{min}/{max}")
    public List<Wrestler> getWrestlersByAltezzaRange(@PathVariable int min, @PathVariable int max) {
        return wrestlerRepository.findByAltezzaBetween(min, max);
    }

    @GetMapping("/peso/{min}/{max}")
    public List<Wrestler> getWrestlersByPesoRange(@PathVariable int min, @PathVariable int max) {
        return wrestlerRepository.findByAltezzaBetween(min, max);
    }

    @GetMapping("/stats")
public Map<String, Object> getWrestlerStats() {
    long totalWrestlers = wrestlerRepository.count();
    Optional<Wrestler> tallestWrestler = wrestlerRepository.findTopByOrderByAltezzaDesc();
    Optional<Wrestler> heaviestWrestler = wrestlerRepository.findTopByOrderByAltezzaDesc();

    Map<String, Object> stats = new HashMap<>();
    stats.put("totalWrestlers", totalWrestlers);
    stats.put("tallestWrestler", tallestWrestler.orElse(null));
    stats.put("heaviestWrestler", heaviestWrestler.orElse(null));

    return stats;
}

@GetMapping("/search/{nome}")
public List<Wrestler> searchWrestlersByNome(@PathVariable String nome) {
    return wrestlerRepository.findByNomeContainingIgnoreCase(nome);
}

@GetMapping("/paged")
public ResponseEntity<Page<Wrestler>> getWrestlersPaged(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "nome") String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    Page<Wrestler> wrestlers = wrestlerRepository.findAll(pageable);
    return ResponseEntity.ok(wrestlers);
}
@GetMapping("/{id}/matches")
public List<Match> getMatchesByWrestlerId(@PathVariable String id) {
    return matchRepository.findByWrestlerId(id);
}

@GetMapping("/{id}/stats/{year}")
public Map<String, Object> getWrestlerStatsByYear(@PathVariable String id, @PathVariable int year) {
    long totalMatches = matchRepository.countByWrestlerIdAndYear(id, year);
    long wins = matchRepository.countByWrestlerIdAndYearAndResult(id, year, "win");
    long losses = totalMatches - wins;
    
    Map<String, Object> stats = new HashMap<>();
    stats.put("totalMatches", totalMatches);
    stats.put("wins", wins);
    stats.put("losses", losses);
    
    return stats;
}
*/