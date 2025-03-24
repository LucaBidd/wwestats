package com.luca.wwestatsspring.service;

import java.time.LocalDate;
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
//----Crud base---
    public List<Wrestler> getAllWrestlers() {
        return repository.findAll();
    }

    public Wrestler getWrestlerById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrestler con ID " + id + " non trovato"));
    }
    
    public void deleteById(String id) {
        repository.deleteById(id);
        System.out.println("Wrestler " + id + " eliminato con successo");
    }
        
    public Wrestler addWrestler(Wrestler wrestler){
        return repository.save(wrestler);
    }
        
    public Wrestler updateWrestler(String id, Wrestler wrestler) {
        return repository.findById(id)
                .map(existingWrestler -> {
                    existingWrestler.setNome(wrestler.getNome());
                    existingWrestler.setDataNascita(wrestler.getDataNascita());
                    existingWrestler.setNazionalita(wrestler.getNazionalita());
                    existingWrestler.setAltezza(wrestler.getAltezza());
                    existingWrestler.setPeso(wrestler.getPeso());
                    existingWrestler.setDebutto(wrestler.getDebutto());
                    return repository.save(existingWrestler);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrestler con ID " + id + " non trovato"));
    }
        
//---Altro---
    
    public void deleteWrtestlerByNome(String nome){
        repository.deleteByNome(nome);
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

    public List<Wrestler> getWrestlerByNomeSimile(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Wrestler> getByPesoBetween(double min, double max){
        return repository.findByPesoBetween(min, max);
    }

    public Optional<Wrestler> getHeaviest(){
        return repository.findTopByOrderByPesoDesc();
    }

    public List<Wrestler> getWrestlerByDebutto(LocalDate debutto){
        return repository.findByDebutto(debutto);
    }

    public List<Wrestler> getWrestlerByCategoria(String categoria){
        return repository.findByCategoria(categoria);
    }

}
