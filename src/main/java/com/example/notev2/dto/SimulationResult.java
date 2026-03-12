package com.example.notev2.dto;

import com.example.notev2.model.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SimulationResult {
    private Candidat candidat;
    private Matiere matiere;
    private List<Note> notes;
    private BigDecimal gap;
    private Parametre matchedParam;
    private BigDecimal finalGrade;
    private boolean usedDefault;
}
