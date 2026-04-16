package com.forrage.app.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    
    private String lieu;
    private String district;
    private String status;
}
