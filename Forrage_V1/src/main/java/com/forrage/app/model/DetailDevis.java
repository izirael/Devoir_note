package com.forrage.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DetailDevis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "devis_id")
    private Devis devis;
    
    @ManyToOne
    @JoinColumn(name = "type_devis_id")
    private TypeDevis typeDevis;
    
    private Double quantite;
    private Double prixUnitaire;
    private Double total;
}
