package com.forrage.app.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Travaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "devis_id")
    private Devis devis;
    
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demande demande;
}
