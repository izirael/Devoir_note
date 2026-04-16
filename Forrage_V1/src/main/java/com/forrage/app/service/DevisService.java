package com.forrage.app.service;

import com.forrage.app.dto.DevisRequestDTO;
import com.forrage.app.model.*;
import com.forrage.app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevisService {
    private final DevisRepository devisRepository;
    private final StatutRepository statutRepository;
    private final DemandeStatutService demandeStatutService;
    private final DetailDevisRepository detailDevisRepository;
    private final TypeDevisRepository typeDevisRepository;
    private final DemandeService demandeService;

    public List<Devis> findAll() {
        return devisRepository.findAll();
    }

    public Optional<Devis> findById(Long id) {
        return devisRepository.findById(id);
    }

    @Transactional
    public Devis save(Devis devis) {
        // Enregistrer le devis
        Devis savedDevis = devisRepository.save(devis);

        // Mettre à jour l'historique de la demande si elle existe
        if (savedDevis.getDemande() != null && savedDevis.getTypeDevis() != null) {
            String statusName = "Devis " + savedDevis.getTypeDevis().getNom() + " Créé";
            Optional<StatutDevis> statusOpt = statutRepository.findByNom(statusName);
            
            if (statusOpt.isPresent()) {
                DemandeStatut history = new DemandeStatut();
                history.setDemande(savedDevis.getDemande());
                history.setStatut(statusOpt.get());
                demandeStatutService.save(history);
                
                // Update the current status of the demande
                Demande demande = savedDevis.getDemande();
                demande.setStatus(statusName);
                // The transaction will handle saving the updated "demande" if it's managed,
                // but let's be explicit if needed.
            }
        }

        return savedDevis;
    }

    @Transactional
    public void saveWithDetails(DevisRequestDTO dto) {
        Demande demande = demandeService.getDemandeById(dto.getDemandeId())
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));

        Devis devis = new Devis();
        devis.setDemande(demande);
        devis.setMontant(dto.getMontantTotal());
        devis.setLibelle(dto.getLibelle());
        devis.setDate(LocalDate.now());

        // Use the first item's type as main type if possible
        if (!dto.getItems().isEmpty()) {
            typeDevisRepository.findByNom(dto.getItems().get(0).getTypeName())
                    .ifPresent(devis::setTypeDevis);
        }

        Devis savedDevis = devisRepository.save(devis);

        for (DevisRequestDTO.DevisItemDTO itemDto : dto.getItems()) {
            DetailDevis detail = new DetailDevis();
            detail.setDevis(savedDevis);
            detail.setQuantite(itemDto.getQuantite());
            detail.setPrixUnitaire(itemDto.getPrixUnitaire());
            detail.setTotal(itemDto.getTotal());
            
            typeDevisRepository.findByNom(itemDto.getTypeName())
                    .ifPresent(detail::setTypeDevis);
            
            detailDevisRepository.save(detail);
        }

        // Update Demande status
        String statusName = "Devis " + (devis.getTypeDevis() != null ? devis.getTypeDevis().getNom() : "Groupé") + " Créé";
        demande.setStatus(statusName);
        
        Optional<StatutDevis> statusOpt = statutRepository.findByNom(statusName);
        if (statusOpt.isPresent()) {
            DemandeStatut history = new DemandeStatut();
            history.setDemande(demande);
            history.setStatut(statusOpt.get());
            demandeStatutService.save(history);
        }
    }

    public void deleteById(Long id) {
        devisRepository.deleteById(id);
    }
}
