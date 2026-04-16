package com.forrage.app.repository;

import com.forrage.app.model.DemandeStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DemandeStatutRepository extends JpaRepository<DemandeStatut, Long> {
    List<DemandeStatut> findByDemandeIdOrderByDateDesc(Long demandeId);
    List<DemandeStatut> findAllByOrderByDateDesc();
}
