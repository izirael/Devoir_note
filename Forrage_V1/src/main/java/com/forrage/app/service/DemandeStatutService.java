package com.forrage.app.service;

import com.forrage.app.model.Demande;
import com.forrage.app.model.DemandeStatut;
import com.forrage.app.repository.DemandeStatutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DemandeStatutService {
    private final DemandeStatutRepository repository;

    public List<DemandeStatut> findAll() {
        return repository.findAllByOrderByDateDesc();
    }

    public List<DemandeStatut> findByDemande(Long demandeId) {
        return repository.findByDemandeIdOrderByDateDesc(demandeId);
    }

    public DemandeStatut save(DemandeStatut status) {
        if (status.getDate() == null) {
            status.setDate(LocalDate.now());
        }
        DemandeStatut saved = repository.save(status);
        
        // Update the current status of the demande
        if (saved.getDemande() != null && saved.getStatut() != null) {
            Demande demande = saved.getDemande();
            demande.setStatus(saved.getStatut().getNom());
            // The repository handles saving if needed
        }
        
        return saved;
    }

    public void deleteById(Long id) {
        DemandeStatut ds = repository.findById(id).orElse(null);
        if (ds != null && ds.getDemande() != null) {
            Demande d = ds.getDemande();
            repository.deleteById(id);
            
            // Optionally update to previous status
            List<DemandeStatut> history = repository.findByDemandeIdOrderByDateDesc(d.getId());
            if (!history.isEmpty()) {
                d.setStatus(history.get(0).getStatut().getNom());
            } else {
                d.setStatus(null);
            }
        } else if (ds != null) {
            repository.deleteById(id);
        }
    }

    public DemandeStatut findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
