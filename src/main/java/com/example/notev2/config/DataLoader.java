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

    @Autowired
    private CorrecteurRepository correcteurRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (candidatRepository.count() == 0) {
                // Candidats
                Candidat c1 = new Candidat(); c1.setNom("Dupont"); c1.setPrenom("Jean"); c1.setMatricule("MAT001");
                Candidat c2 = new Candidat(); c2.setNom("Durand"); c2.setPrenom("Marie"); c2.setMatricule("MAT002");
                candidatRepository.save(c1); candidatRepository.save(c2);

                // Correcteurs
                Correcteur cor1 = new Correcteur(); cor1.setNom("Louis");
                Correcteur cor2 = new Correcteur(); cor2.setNom("Nyaina");
                Correcteur cor3 = new Correcteur(); cor3.setNom("Mikolo");
                correcteurRepository.saveAll(java.util.List.of(cor1, cor2, cor3));

                // Matieres
                Matiere m1 = new Matiere(); m1.setNom("Mathématiques"); m1.setCoefficient(new BigDecimal("2"));
                Matiere m2 = new Matiere(); m2.setNom("Français"); m1.setCoefficient(new BigDecimal("1"));
                matiereRepository.save(m1); matiereRepository.save(m2);

                // Notes for Jean in Math (Multi-correction)
                Note n1 = new Note(); n1.setCandidat(c1); n1.setMatiere(m1); n1.setCorrecteur(cor1); n1.setValeurNote(new BigDecimal("12"));
                Note n2 = new Note(); n2.setCandidat(c1); n2.setMatiere(m1); n2.setCorrecteur(cor2); n2.setValeurNote(new BigDecimal("14"));
                Note n3 = new Note(); n3.setCandidat(c1); n3.setMatiere(m1); n3.setCorrecteur(cor3); n3.setValeurNote(new BigDecimal("11"));
                
                // Notes for Marie in Math
                Note n4 = new Note(); n4.setCandidat(c2); n4.setMatiere(m1); n4.setCorrecteur(cor1); n4.setValeurNote(new BigDecimal("9"));
                
                noteRepository.saveAll(java.util.List.of(n1, n2, n3, n4));

                // Operators and Parameters (Barèmes)
                Operateur opAdd = new Operateur(); opAdd.setNom("Addition"); opAdd.setSymbole("+");
                Operateur opSub = new Operateur(); opSub.setNom("Soustraction"); opSub.setSymbole("-");
                operateurRepository.saveAll(java.util.List.of(opAdd, opSub));

                Parametre p1 = new Parametre();
                p1.setOperateur(opSub);
                p1.setMin(0);
                p1.setMax(2);
                
                Parametre p2 = new Parametre();
                p2.setOperateur(opAdd);
                p2.setMin(3);
                p2.setMax(10);
                
                parametreRepository.saveAll(java.util.List.of(p1, p2));
            }
        };
    }
}
