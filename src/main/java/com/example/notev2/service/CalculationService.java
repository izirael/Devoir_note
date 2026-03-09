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

        // Sort notes by value to calculate gaps accurately if needed, 
        // but requirement says "écart entre les notes des correcteurs".
        // Let's assume we take the gap between the first two corrections for the rule.
        BigDecimal n1 = notes.get(0).getValeurNote();
        BigDecimal n2 = notes.get(1).getValeurNote();
        BigDecimal gap = n1.subtract(n2).abs();

        List<Parametre> ranges = parametreRepository.findAll();
        Parametre matchedParam = null;
        for (Parametre p : ranges) {
            if (gap.intValue() >= p.getMin() && gap.intValue() <= p.getMax()) {
                matchedParam = p;
                break;
            }
        }

        if (matchedParam == null) {
            // Default: average of all notes if no range matches
            BigDecimal sum = BigDecimal.ZERO;
            for (Note n : notes) sum = sum.add(n.getValeurNote());
            return sum.divide(new BigDecimal(notes.size()), 2, RoundingMode.HALF_UP);
        }

        // Apply operator of the matched range to the first note (base note)
        String sym = matchedParam.getOperateur().getSymbole();
        // The rule says "appliquer l'opérateur associé pour obtenir le résultat final"
        // Usually, this might mean (Note1 + Note2) / 2 or similar, 
        // but here we follow the "apply operator to get result" literally.
        // Let's assume Result = n1 [OP] n2.
        return applyOperator(n1, n2, sym);
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
