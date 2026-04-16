package com.forrage.app.service;

import com.forrage.app.model.Demande;
import com.forrage.app.model.DemandeStatut;
import com.forrage.app.model.StatutDevis;
import com.forrage.app.repository.DemandeRepository;
import com.forrage.app.repository.StatutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private StatutRepository statutRepository;

    @Autowired
    private DemandeStatutService demandeStatutService;

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    public Optional<Demande> getDemandeById(Long id) {
        return demandeRepository.findById(id);
    }

    @Transactional
    public Demande saveDemande(Demande demande) {
        boolean isNew = demande.getId() == null;
        Demande savedDemande = demandeRepository.save(demande);
        
        if (isNew) {
            Optional<StatutDevis> statusOpt = statutRepository.findByNom("Nouveau");
            if (statusOpt.isPresent()) {
                DemandeStatut history = new DemandeStatut();
                history.setDemande(savedDemande);
                history.setStatut(statusOpt.get());
                demandeStatutService.save(history);
            }
        }
        
        return savedDemande;
    }

    public void deleteDemande(Long id) {
        demandeRepository.deleteById(id);
    }

    public Demande findById(Long id) {
        return demandeRepository.findById(id).orElse(null);
    }
}
