package com.forrage.app.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class DemandeStatut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "travaux_id")
    private Travaux travaux;
    
    @ManyToOne
    @JoinColumn(name = "statut_id")
    private StatutDevis statut;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demande demande;
    
    private LocalDate date;
    private String observation;
}
