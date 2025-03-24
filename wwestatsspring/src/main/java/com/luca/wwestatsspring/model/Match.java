package com.luca.wwestatsspring.model;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // getter, setter, toString, equals e hashCode
@Document // la classe è un documento di MongoDB
public class Match {
    
    @Id
    private String id;

    private TipoMatch tipo;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime durata;

    private Stipulazione stipulazione;

    private List<String> partecipanti; // lista di ID wrestler

    private List<String> vincitori; // lista di ID wrestler vincitori

    private String eventoId;

    private String posizione; // "Opener", "Main Event", "Mid-card"

    // Metodo per validare il match
    public boolean isValid() {
        // Il match deve avere almeno 2 partecipanti e almeno 1 vincitore
        return partecipanti != null && partecipanti.size() >= 2 &&
               vincitori != null && vincitori.size() >= 1;
    }

    // Metodo per ottenere la durata in minuti (opzionale)
    public long getDurataInMinuti() {
        return this.durata.getHour() * 60 + this.durata.getMinute();
    }

    // Metodo per definire la posizione del match
    public void setPosizione(int posizione) {
        if (posizione == 1) {
            this.posizione = "Opener";
        } else if (posizione == 2) {
            this.posizione = "Main Event";
        } else {
            this.posizione = "Mid-card";
        }
    }

    // Metodo per ottenere una descrizione formattata del match
    public String getDescrizioneMatch() {
        return String.format("%s match - %s stipulazione, durata: %s",
                tipo.name(), stipulazione.name(), durata.toString());
    }
}
