package com.forrage.app.controller;

import com.forrage.app.model.StatutDevis;
import com.forrage.app.service.StatutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/statuts")
@RequiredArgsConstructor
public class StatutController {
    private final StatutService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("statuts", service.findAll());
        model.addAttribute("statut", new StatutDevis());
        return "statuts/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute StatutDevis statut) {
        service.save(statut);
        return "redirect:/statuts";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("statuts", service.findAll());
        model.addAttribute("statut", service.findById(id));
        return "statuts/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/statuts";
    }
}
