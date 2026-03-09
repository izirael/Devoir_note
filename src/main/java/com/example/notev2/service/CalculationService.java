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

    public BigDecimal calculateTotalDifference(Candidat candidat, Matiere matiere) {
        List<Note> notes = noteRepository.findByCandidatAndMatiere(candidat, matiere);
        if (notes.isEmpty() || notes.size() < 2) {
            return BigDecimal.ZERO;
        }

        List<Parametre> parametres = parametreRepository.findAll();
        if (parametres.isEmpty()) {
            // Default logic: sum of differences between consecutive notes
            BigDecimal totalDiff = BigDecimal.ZERO;
            for (int i = 1; i < notes.size(); i++) {
                totalDiff = totalDiff.add(notes.get(i).getValeurNote().subtract(notes.get(i - 1).getValeurNote()).abs());
            }
            return totalDiff;
        }

        // Apply dynamic logic from Parametre
        // For simplicity, we assume the first parameter defines the operation to apply iteratively
        Parametre p = parametres.get(0);
        String sym = p.getOperateur().getSymbole();
        
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 1; i < notes.size(); i++) {
            BigDecimal v1 = notes.get(i-1).getValeurNote();
            BigDecimal v2 = notes.get(i).getValeurNote();
            result = result.add(applyOperator(v1, v2, sym).abs());
        }

        return result;
    }

    private BigDecimal applyOperator(BigDecimal v1, BigDecimal v2, String symbole) {
        switch (symbole) {
            case "+": return v1.add(v2);
            case "-": return v1.subtract(v2);
            case "*": return v1.multiply(v2);
            case "/": 
                if (v2.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
                return v1.divide(v2, 2, RoundingMode.HALF_UP);
            default: return v1.subtract(v2);
        }
    }
}
