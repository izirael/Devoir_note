package com.example.notev2.controller;

import com.example.notev2.model.*;
import com.example.notev2.service.GradeService;
import com.example.notev2.repository.ParametreRepository;
import com.example.notev2.repository.OperateurRepository;
import com.example.notev2.repository.CorrecteurRepository;
import com.example.notev2.repository.CandidatRepository;
import com.example.notev2.repository.NoteRepository;
import com.example.notev2.repository.MatiereRepository;
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

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("candidats", gradeService.getAllCandidats());
        return "candidat_list";
    }

    @PostMapping("/candidat/save")
    public String saveCandidat(@ModelAttribute Candidat candidat) {
        candidatRepository.save(candidat);
        return "redirect:/";
    }

    @GetMapping("/candidat/delete/{id}")
    public String deleteCandidat(@PathVariable Long id) {
        candidatRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/candidat/{id}")
    public String viewDetails(@PathVariable Long id, Model model) {
        Candidat c = candidatRepository.findById(id).orElse(null);
        if (c == null) return "redirect:/";

        List<Matiere> matieres = gradeService.getAllMatieres();
        Map<Matiere, BigDecimal> calculatedGrades = gradeService.getCalculatedGradesForCandidat(c);
        
        // Find raw notes for this candidate to show in details
        List<Note> rawNotes = noteRepository.findByCandidat(c);

        model.addAttribute("candidat", c);
        model.addAttribute("matieres", matieres);
        model.addAttribute("calculatedGrades", calculatedGrades);
        model.addAttribute("rawNotes", rawNotes);
        return "candidat_details";
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

    @GetMapping("/simulate")
    public String showSimulationForm(Model model) {
        model.addAttribute("candidats", gradeService.getAllCandidats());
        model.addAttribute("matieres", gradeService.getAllMatieres());
        return "simulation";
    }

    @PostMapping("/simulate")
    public String runSimulation(@RequestParam Long idCandidat, @RequestParam Long idMatiere, Model model) {
        Candidat c = candidatRepository.findById(idCandidat).orElse(null);
        Matiere m = matiereRepository.findById(idMatiere).orElse(null);
        
        if (c != null && m != null) {
            model.addAttribute("result", gradeService.simulateGrade(c, m));
        }
        
        model.addAttribute("candidats", gradeService.getAllCandidats());
        model.addAttribute("matieres", gradeService.getAllMatieres());
        model.addAttribute("selectedCandId", idCandidat);
        model.addAttribute("selectedMatId", idMatiere);
        return "simulation";
    }
}
