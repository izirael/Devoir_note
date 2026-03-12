package com.example.notev2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "parametre")
public class Parametre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_operateur")
    private Operateur operateur;

    @ManyToOne
    @JoinColumn(name = "id_matiere")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "id_resolution")
    private Resolution resolution;

    private Integer gap;
}
