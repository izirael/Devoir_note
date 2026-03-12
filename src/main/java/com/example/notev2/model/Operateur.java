package com.example.notev2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "operateur")
public class Operateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String symbole;
}
