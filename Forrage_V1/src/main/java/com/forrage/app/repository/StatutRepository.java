package com.forrage.app.repository;

import com.forrage.app.model.StatutDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StatutRepository extends JpaRepository<StatutDevis, Long> {
    Optional<StatutDevis> findByNom(String nom);
}
