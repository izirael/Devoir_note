package com.example.notev2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "correcteur")
public class Correcteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
}
