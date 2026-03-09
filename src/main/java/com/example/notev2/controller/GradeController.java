package com.example.notev2.controller;

import com.example.notev2.model.*;
import com.example.notev2.service.GradeService;
import com.example.notev2.repository.ParametreRepository;
import com.example.notev2.repository.OperateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ParametreRepository parametreRepository;

    @Autowired
    private OperateurRepository operateurRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Candidat> candidats = gradeService.getAllCandidats();
        List<Matiere> matieres = gradeService.getAllMatieres();
        
        // Map of Candidat ID -> (Matiere -> Grade)
        Map<Long, Map<Matiere, BigDecimal>> gradeMap = new HashMap<>();
        for (Candidat c : candidats) {
            gradeMap.put(c.getId(), gradeService.getCalculatedGradesForCandidat(c));
        }

        model.addAttribute("candidats", candidats);
        model.addAttribute("matieres", matieres);
        model.addAttribute("gradeMap", gradeMap);
        return "grades_list";
    }

    @GetMapping("/config")
    public String config(Model model) {
        model.addAttribute("parametres", parametreRepository.findAll());
        model.addAttribute("operateurs", operateurRepository.findAll());
        return "config";
    }

    @PostMapping("/config/update")
    public String updateConfig(@RequestParam Long id, @RequestParam Long idOperateur) {
        Parametre p = parametreRepository.findById(id).orElse(new Parametre());
        Operateur op = operateurRepository.findById(idOperateur).orElse(null);
        if (op != null) {
            p.setOperateur(op);
            parametreRepository.save(p);
        }
        return "redirect:/config";
    }
}
