package com.forrage.app.controller;

import com.forrage.app.model.Demande;
import com.forrage.app.service.DemandeService;
import com.forrage.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listDemandes(Model model) {
        model.addAttribute("demandes", demandeService.getAllDemandes());
        return "demandes/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("demande", new Demande());
        model.addAttribute("clients", clientService.getAllClients());
        return "demandes/form";
    }

    @PostMapping("/save")
    public String saveDemande(@ModelAttribute("demande") Demande demande) {
        demandeService.saveDemande(demande);
        return "redirect:/demandes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Demande demande = demandeService.getDemandeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid demande Id:" + id));
        model.addAttribute("demande", demande);
        model.addAttribute("clients", clientService.getAllClients());
        return "demandes/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDemande(@PathVariable("id") Long id) {
        demandeService.deleteDemande(id);
        return "redirect:/demandes";
    }
}
