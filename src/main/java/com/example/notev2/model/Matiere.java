package com.example.notev2.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "matiere")
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatiere;

    private String nom;

    @Column(columnDefinition = "NUMERIC DEFAULT 1")
    private BigDecimal coefficient = BigDecimal.ONE;
}
