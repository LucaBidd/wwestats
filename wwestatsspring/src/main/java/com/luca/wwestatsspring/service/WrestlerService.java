package com.luca.wwestatsspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.luca.wwestatsspring.model.Wrestler;
import com.luca.wwestatsspring.repository.WrestlerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WrestlerService {
    
    private final WrestlerRepository repository;

    public List<Wrestler> getAllWrestlers() {
        return repository.findAll();
    }

    public Optional<Wrestler> getWrestlerById(String id){
        return repository.findById(id);
    }
    
    public Optional<Wrestler> getWrestlerByNome(String nome) {
        return repository.findWrestlerByNome(nome);
    }
    
    public void deleteWrtestlerByNome(String nome){
        repository.deleteByNome(nome);
    }

    public void deleteById(String id){
        // Verifica se esiste un wrestler con quel nome
        Optional<Wrestler> w = repository.findById(id);
        if (w.isPresent()) {
            repository.deleteById(id);
            System.out.println("Wrestler " + id +" eliminato con successo");
        } else {
            System.out.println("Wrestler " + id +" non trovato");
        }
    }
    
    public Wrestler addWrestler(Wrestler wrestler){
        return repository.save(wrestler);
    }
    
    public Wrestler updateWrestler(String id, Wrestler w) {
        return repository.findById(id).map(existingWrestler -> {
            existingWrestler.setNome(w.getNome());
            existingWrestler.setDataNascita(w.getDataNascita());
            existingWrestler.setNazionalita(w.getNazionalita());
            existingWrestler.setAltezza(w.getAltezza());
            existingWrestler.setPeso(w.getPeso());
            existingWrestler.setDebutto(w.getDebutto());

            System.out.println("Wrestler modificato correttamente");
            return repository.save(existingWrestler); // Usare un repository coerente
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrestler con ID " + id + " non trovato"));
    }

    public List<Wrestler> getByNazionalita(String n){
        return repository.findByNazionalita(n);
    }

    public List<Wrestler> getByAltezzaBetween(int min, int max){
        return repository.findByAltezzaBetween(min, max);
    }

    public Optional<Wrestler> getTallest(){
        return repository.findTopByOrderByAltezzaDesc();
    }

    public List<Wrestler> getWrtestlerByNomeSimile(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    }

}
