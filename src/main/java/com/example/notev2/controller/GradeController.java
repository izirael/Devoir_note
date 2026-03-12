package com.example.notev2.controller;

import com.example.notev2.model.*;
import com.example.notev2.service.GradeService;
import com.example.notev2.repository.ParametreRepository;
import com.example.notev2.repository.OperateurRepository;
import com.example.notev2.repository.CorrecteurRepository;
import com.example.notev2.repository.CandidatRepository;
import com.example.notev2.repository.NoteRepository;
import com.example.notev2.repository.MatiereRepository;
import com.example.notev2.repository.ResolutionRepository;
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

    @Autowired
    private ResolutionRepository resolutionRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("candidats", gradeService.getAllCandidats());
        return "candidat_list";
    }

    @PostMapping("/admin/candidats/save")
    public String saveCandidatAdmin(@ModelAttribute Candidat candidat) {
        candidatRepository.save(candidat);
        return "redirect:/admin/candidats";
    }

    @GetMapping("/admin/candidats")
    public String listCandidatsAdmin(Model model) {
        model.addAttribute("candidats", candidatRepository.findAll());
        return "admin_candidats";
    }

    @GetMapping("/admin/candidats/edit/{id}")
    public String editCandidatAdmin(@PathVariable Long id, Model model) {
        Candidat c = candidatRepository.findById(id).orElse(null);
        if (c != null) {
            model.addAttribute("editCandidat", c);
        }
        model.addAttribute("candidats", candidatRepository.findAll());
        return "admin_candidats";
    }

    @GetMapping("/admin/candidats/delete/{id}")
    public String deleteCandidatAdmin(@PathVariable Long id) {
        candidatRepository.deleteById(id);
        return "redirect:/admin/candidats";
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

    @GetMapping("/admin/operateurs/edit/{id}")
    public String editOperateur(@PathVariable Long id, Model model) {
        Operateur op = operateurRepository.findById(id).orElse(null);
        if (op != null) {
            model.addAttribute("editOperateur", op);
        }
        model.addAttribute("operateurs", operateurRepository.findAll());
        return "admin_operateurs";
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
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("resolutions", resolutionRepository.findAll());
        model.addAttribute("operateurs", operateurRepository.findAll());
        return "admin_params";
    }

    @GetMapping("/admin/parametres/edit/{id}")
    public String editParametre(@PathVariable Long id, Model model) {
        Parametre p = parametreRepository.findById(id).orElse(null);
        if (p != null) {
            model.addAttribute("editParametre", p);
        }
        model.addAttribute("parametres", parametreRepository.findAll());
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("resolutions", resolutionRepository.findAll());
        model.addAttribute("operateurs", operateurRepository.findAll());
        return "admin_params";
    }

    // --- Admin CRUD for Notes (Raw Grades) ---
    @GetMapping("/admin/notes")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteRepository.findAll());
        model.addAttribute("candidats", candidatRepository.findAll());
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("correcteurs", correcteurRepository.findAll());
        return "admin_notes";
    }

    @PostMapping("/admin/notes/save")
    public String saveNote(@ModelAttribute Note note) {
        noteRepository.save(note);
        return "redirect:/admin/notes";
    }

    @GetMapping("/admin/notes/edit/{id}")
    public String editNote(@PathVariable Long id, Model model) {
        Note n = noteRepository.findById(id).orElse(null);
        if (n != null) {
            model.addAttribute("editNote", n);
        }
        model.addAttribute("notes", noteRepository.findAll());
        model.addAttribute("candidats", candidatRepository.findAll());
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("correcteurs", correcteurRepository.findAll());
        return "admin_notes";
    }

    @GetMapping("/admin/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return "redirect:/admin/notes";
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

    @GetMapping("/grades")
    public String listAllGrades(Model model) {
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
}
