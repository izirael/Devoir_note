package com.example.notev2.service;

import com.example.notev2.model.*;
import com.example.notev2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GradeService {

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private CalculationService calculationService;

    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    public Map<Matiere, BigDecimal> getCalculatedGradesForCandidat(Candidat candidat) {
        Map<Matiere, BigDecimal> results = new HashMap<>();
        List<Matiere> matieres = matiereRepository.findAll();
        for (Matiere m : matieres) {
            results.put(m, calculationService.calculateTotalDifference(candidat, m));
        }
        return results;
    }

    public void saveNote(Note note) {
        noteRepository.save(note);
    }
}
