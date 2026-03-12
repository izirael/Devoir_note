package com.example.notev2.service;

import com.example.notev2.model.*;
import com.example.notev2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CalculationService {

    @Autowired
    private ParametreRepository parametreRepository;

    @Autowired
    private NoteRepository noteRepository;

    public BigDecimal calculateDynamicGrade(Candidat candidat, Matiere matiere) {
        List<Note> notes = noteRepository.findByCandidatAndMatiere(candidat, matiere);
        if (notes.isEmpty()) return BigDecimal.ZERO;
        if (notes.size() == 1) return notes.get(0).getValeurNote();

        BigDecimal totalGap = calculateTotalGap(notes);

        // Find parameters for this subject, sorted by gap threshold asc
        List<Parametre> params = parametreRepository.findByMatiereOrderByGapAsc(matiere);
        
        Parametre matchedParam = null;
        for (Parametre p : params) {
            // Requirement says: compare total to 'gap' in parameter table to select resolution
            // We assume "select where totalGap is within threshold"
            if (totalGap.intValue() <= p.getGap()) {
                matchedParam = p;
                break;
            }
        }

        if (matchedParam == null) {
            // Default to Average if no threshold is met (fallback)
            return calculateAggregate(notes, "AVG");
        }

        return calculateAggregate(notes, matchedParam.getResolution().getNom());
    }

    public com.example.notev2.dto.SimulationResult simulateGrade(Candidat candidat, Matiere matiere) {
        com.example.notev2.dto.SimulationResult result = new com.example.notev2.dto.SimulationResult();
        result.setCandidat(candidat);
        result.setMatiere(matiere);
        
        List<Note> notes = noteRepository.findByCandidatAndMatiere(candidat, matiere);
        result.setNotes(notes);
        
        if (notes.isEmpty()) {
            result.setFinalGrade(BigDecimal.ZERO);
            return result;
        }
        if (notes.size() == 1) {
            result.setFinalGrade(notes.get(0).getValeurNote());
            return result;
        }

        BigDecimal totalGap = calculateTotalGap(notes);
        result.setGap(totalGap);

        List<Parametre> params = parametreRepository.findByMatiereOrderByGapAsc(matiere);
        Parametre matchedParam = null;
        for (Parametre p : params) {
            if (totalGap.intValue() <= p.getGap()) {
                matchedParam = p;
                break;
            }
        }
        result.setMatchedParam(matchedParam);

        if (matchedParam == null) {
            result.setUsedDefault(true);
            result.setFinalGrade(calculateAggregate(notes, "AVG"));
        } else {
            result.setFinalGrade(calculateAggregate(notes, matchedParam.getResolution().getNom()));
        }

        return result;
    }

    private BigDecimal calculateTotalGap(List<Note> notes) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < notes.size(); i++) {
            for (int j = i + 1; j < notes.size(); j++) {
                total = total.add(notes.get(i).getValeurNote().subtract(notes.get(j).getValeurNote()).abs());
            }
        }
        return total;
    }

    private BigDecimal calculateAggregate(List<Note> notes, String operator) {
        if (notes.isEmpty()) return BigDecimal.ZERO;
        
        String op = operator.toUpperCase();
        if (op.equals("MIN")) {
            return notes.stream().map(Note::getValeurNote).min(BigDecimal::compareTo).get();
        } else if (op.equals("MAX")) {
            return notes.stream().map(Note::getValeurNote).max(BigDecimal::compareTo).get();
        } else {
            // Default to AVG
            BigDecimal sum = BigDecimal.ZERO;
            for (Note n : notes) sum = sum.add(n.getValeurNote());
            return sum.divide(new BigDecimal(notes.size()), 2, RoundingMode.HALF_UP);
        }
    }
}
