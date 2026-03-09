package com.example.notev2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "candidat")
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @Column(unique = true)
    private String matricule;
}
