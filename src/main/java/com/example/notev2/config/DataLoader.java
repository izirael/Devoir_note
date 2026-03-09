package com.example.notev2.config;

import com.example.notev2.model.*;
import com.example.notev2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Autowired
    private OperateurRepository operateurRepository;

    @Autowired
    private ParametreRepository parametreRepository;

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (candidatRepository.count() == 0) {
                // Candidats
                Candidat c1 = new Candidat(); c1.setNom("Dupont"); c1.setPrenom("Jean"); c1.setMatricule("MAT001");
                Candidat c2 = new Candidat(); c2.setNom("Durand"); c2.setPrenom("Marie"); c2.setMatricule("MAT002");
                candidatRepository.save(c1); candidatRepository.save(c2);

                // Matieres
                Matiere m1 = new Matiere(); m1.setNom("Mathématiques"); m1.setCoefficient(new BigDecimal("2"));
                Matiere m2 = new Matiere(); m2.setNom("Français"); m2.setCoefficient(new BigDecimal("1"));
                matiereRepository.save(m1); matiereRepository.save(m2);

                // Notes for Jean in Math (Multi-correction)
                Note n1 = new Note(); n1.setCandidat(c1); n1.setMatiere(m1); n1.setIdCorrecteur(1); n1.setValeurNote(new BigDecimal("12"));
                Note n2 = new Note(); n2.setCandidat(c1); n2.setMatiere(m1); n2.setIdCorrecteur(2); n2.setValeurNote(new BigDecimal("14"));
                Note n3 = new Note(); n3.setCandidat(c1); n3.setMatiere(m1); n3.setIdCorrecteur(3); n3.setValeurNote(new BigDecimal("11"));
                
                // Notes for Marie in Math
                Note n4 = new Note(); n4.setCandidat(c2); n4.setMatiere(m1); n4.setIdCorrecteur(1); n4.setValeurNote(new BigDecimal("9"));
                
                noteRepository.save(n1); noteRepository.save(n2); noteRepository.save(n3); noteRepository.save(n4);

                // Operators (if not in schema.sql or as fallback)
                if (operateurRepository.count() == 0) {
                    Operateur opSub = new Operateur(); opSub.setNom("Soustraction"); opSub.setSymbole("-");
                    operateurRepository.save(opSub);
                    
                    Parametre p = new Parametre();
                    p.setOperateur(opSub);
                    p.setValeurGauche("PREV_NOTE");
                    p.setValeurDroite("NEXT_NOTE");
                    parametreRepository.save(p);
                }
            }
        };
    }
}
