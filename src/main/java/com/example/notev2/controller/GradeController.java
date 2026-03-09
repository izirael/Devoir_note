package com.example.notev2.controller;

import com.example.notev2.model.*;
import com.example.notev2.service.GradeService;
import com.example.notev2.repository.ParametreRepository;
import com.example.notev2.repository.OperateurRepository;
import com.example.notev2.repository.CorrecteurRepository;
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

    @Autowired
    private CorrecteurRepository correcteurRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Candidat> candidats = gradeService.getAllCandidats();
        List<Matiere> matieres = gradeService.getAllMatieres();
        
        Map<Long, Map<Matiere, BigDecimal>> gradeMap = new HashMap<>();
        for (Candidat c : candidats) {
            gradeMap.put(c.getId(), gradeService.getCalculatedGradesForCandidat(c));
        }

        model.addAttribute("candidats", candidats);
        model.addAttribute("matieres", matieres);
        model.addAttribute("gradeMap", gradeMap);
        return "grades_list";
    }

    // --- Admin CRUD for Operateurs ---
    @GetMapping("/admin/operateurs")
    public String listOperateurs(Model model) {
        model.addAttribute("operateurs", operateurRepository.findAll());
        return "admin_operateurs";
    }

    @PostMapping("/admin/operateurs/save")
    public String saveOperateur(@ModelAttribute Operateur operateur) {
        operateurRepository.save(operateur);
        return "redirect:/admin/operateurs";
    }

    @GetMapping("/admin/operateurs/delete/{id}")
    public String deleteOperateur(@PathVariable Long id) {
        operateurRepository.deleteById(id);
        return "redirect:/admin/operateurs";
    }

    // --- Admin CRUD for Parametres (Barèmes) ---
    @GetMapping("/admin/parametres")
    public String listParametres(Model model) {
        model.addAttribute("parametres", parametreRepository.findAll());
        model.addAttribute("operateurs", operateurRepository.findAll());
        return "admin_params";
    }

    @PostMapping("/admin/parametres/save")
    public String saveParametre(@RequestParam(required = false) Long id, @RequestParam Long idOperateur, @RequestParam Integer min, @RequestParam Integer max) {
        Parametre p = (id != null) ? parametreRepository.findById(id).orElse(new Parametre()) : new Parametre();
        Operateur op = operateurRepository.findById(idOperateur).orElse(null);
        if (op != null) {
            p.setOperateur(op);
            p.setMin(min);
            p.setMax(max);
            parametreRepository.save(p);
        }
        return "redirect:/admin/parametres";
    }

    @GetMapping("/admin/parametres/delete/{id}")
    public String deleteParametre(@PathVariable Long id) {
        parametreRepository.deleteById(id);
        return "redirect:/admin/parametres";
    }
}
