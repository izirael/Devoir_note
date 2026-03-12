package com.example.notev2.repository;

import com.example.notev2.model.Parametre;
import com.example.notev2.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long> {
    List<Parametre> findByMatiereOrderByGapAsc(Matiere matiere);
}
