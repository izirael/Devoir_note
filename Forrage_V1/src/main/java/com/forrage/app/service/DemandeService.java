package com.forrage.app.service;

import com.forrage.app.model.Demande;
import com.forrage.app.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    public Optional<Demande> getDemandeById(Long id) {
        return demandeRepository.findById(id);
    }

    public Demande saveDemande(Demande demande) {
        return demandeRepository.save(demande);
    }

    public void deleteDemande(Long id) {
        demandeRepository.deleteById(id);
    }
}
