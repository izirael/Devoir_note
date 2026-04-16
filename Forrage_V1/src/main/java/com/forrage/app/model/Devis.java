package com.forrage.app.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demande demande;
    
    private Double montant;
    
    @ManyToOne
    @JoinColumn(name = "statut_id")
    private StatutDevis statut;

    @ManyToOne
    @JoinColumn(name = "type_devis_id")
    private TypeDevis typeDevis;

    private String libelle;
}
