package com.forrage.app.repository;

import com.forrage.app.model.TypeDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TypeDevisRepository extends JpaRepository<TypeDevis, Long> {
    Optional<TypeDevis> findByNom(String nom);
}
