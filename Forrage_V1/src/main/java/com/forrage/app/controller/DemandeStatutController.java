package com.forrage.app.controller;

import com.forrage.app.model.Demande;
import com.forrage.app.model.DemandeStatut;
import com.forrage.app.service.DemandeStatutService;
import com.forrage.app.service.DemandeService;
import com.forrage.app.service.StatutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DemandeStatutController {
    private final DemandeStatutService service;
    private final DemandeService demandeService;
    private final StatutService statutService;

    @GetMapping("/demande-status")
    public String listAll(Model model) {
        model.addAttribute("statuses", service.findAll());
        return "demande_status/list";
    }

    @GetMapping("/demandes/{id}/status")
    public String history(@PathVariable Long id, Model model) {
        model.addAttribute("demande", demandeService.findById(id));
        model.addAttribute("statuses", service.findByDemande(id));
        model.addAttribute("availableStatuts", statutService.findAll());
        
        DemandeStatut newStatus = new DemandeStatut();
        newStatus.setDemande(demandeService.findById(id));
        model.addAttribute("newStatus", newStatus);
        
        return "demande_status/history";
    }

    @PostMapping("/demande-status/save")
    public String save(@ModelAttribute DemandeStatut status) {
        service.save(status);
        return "redirect:/demandes/" + status.getDemande().getId() + "/status";
    }

    @GetMapping("/demande-status/delete/{id}")
    public String delete(@PathVariable Long id) {
        DemandeStatut ds = service.findById(id);
        Long demandeId = ds.getDemande().getId();
        service.deleteById(id);
        return "redirect:/demandes/" + demandeId + "/status";
    }
}
