package com.forrage.app.controller;

import com.forrage.app.dto.DevisRequestDTO;
import com.forrage.app.model.Devis;
import com.forrage.app.model.Demande;
import com.forrage.app.repository.TypeDevisRepository;
import com.forrage.app.service.PromotionService;
import com.forrage.app.service.DevisService;
import com.forrage.app.service.DemandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/devis")
@RequiredArgsConstructor
public class DevisController {

    private final TypeDevisRepository typeDevisRepository;
    private final DevisService devisService;
    private final DemandeService demandeService;
    private final PromotionService promotionService;

    @GetMapping("/calculator")
    public String showCalculator(@RequestParam(value = "demandeId", required = false) Long demandeId, Model model) {
        if (demandeId != null) {
            demandeService.getDemandeById(demandeId).ifPresent(d -> model.addAttribute("demande", d));
        }
        model.addAttribute("demandes", demandeService.getAllDemandes());
        model.addAttribute("types", typeDevisRepository.findAll());
        model.addAttribute("promotion", promotionService.getActivePromotion().orElse(null));
        return "devis/calculator";
    }

    @GetMapping("/new")
    public String showCreateForm(@RequestParam("demandeId") Long demandeId, Model model) {
        Optional<Demande> demandeOpt = demandeService.getDemandeById(demandeId);
        if (demandeOpt.isEmpty()) {
            return "redirect:/demandes";
        }
        
        Devis devis = new Devis();
        devis.setDemande(demandeOpt.get());
        
        model.addAttribute("devis", devis);
        model.addAttribute("demande", demandeOpt.get());
        model.addAttribute("types", typeDevisRepository.findAll());
        return "devis/form";
    }

    @PostMapping("/save")
    public String saveDevis(@ModelAttribute("devis") Devis devis) {
        devisService.save(devis);
        return "redirect:/demandes";
    }

    @PostMapping("/save-with-details")
    @ResponseBody
    public ResponseEntity<?> saveWithDetails(@RequestBody DevisRequestDTO dto) {
        try {
            devisService.saveWithDetails(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public String listDevis(Model model) {
        model.addAttribute("devisList", devisService.findAll());
        return "devis/list";
    }
}
