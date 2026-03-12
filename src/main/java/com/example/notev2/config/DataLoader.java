package com.example.notev2.config;

import com.example.notev2.model.*;
import com.example.notev2.repository.CandidatRepository;
import com.example.notev2.repository.CorrecteurRepository;
import com.example.notev2.repository.MatiereRepository;
import com.example.notev2.repository.NoteRepository;
import com.example.notev2.repository.OperateurRepository;
import com.example.notev2.repository.ParametreRepository;
import com.example.notev2.repository.ResolutionRepository;
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

    @Autowired
    private ResolutionRepository resolutionRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (candidatRepository.count() == 0) {
                // ... (Candidats, Correcteurs, Matieres, Notes setup is same)
                Candidat c1 = new Candidat(); c1.setNom("Dupont"); c1.setPrenom("Jean"); c1.setMatricule("MAT001");
                Candidat c2 = new Candidat(); c2.setNom("Durand"); c2.setPrenom("Marie"); c2.setMatricule("MAT002");
                candidatRepository.save(c1); candidatRepository.save(c2);

                Correcteur cor1 = new Correcteur(); cor1.setNom("Louis");
                Correcteur cor2 = new Correcteur(); cor2.setNom("Nyaina");
                Correcteur cor3 = new Correcteur(); cor3.setNom("Mikolo");
                correcteurRepository.saveAll(java.util.List.of(cor1, cor2, cor3));

                Matiere m1 = new Matiere(); m1.setNom("Mathématiques"); m1.setCoefficient(new BigDecimal("2"));
                Matiere m2 = new Matiere(); m2.setNom("Français"); m2.setCoefficient(new BigDecimal("1"));
                matiereRepository.save(m1); matiereRepository.save(m2);

                Note n1 = new Note(); n1.setCandidat(c1); n1.setMatiere(m1); n1.setCorrecteur(cor1); n1.setValeurNote(new BigDecimal("12"));
                Note n2 = new Note(); n2.setCandidat(c1); n2.setMatiere(m1); n2.setCorrecteur(cor2); n2.setValeurNote(new BigDecimal("14"));
                Note n3 = new Note(); n3.setCandidat(c1); n3.setMatiere(m1); n3.setCorrecteur(cor3); n3.setValeurNote(new BigDecimal("11"));
                Note n4 = new Note(); n4.setCandidat(c2); n4.setMatiere(m1); n4.setCorrecteur(cor1); n4.setValeurNote(new BigDecimal("9"));
                noteRepository.saveAll(java.util.List.of(n1, n2, n3, n4));

                // Resolutions
                Resolution resMin = new Resolution(); resMin.setNom("MIN");
                Resolution resAvg = new Resolution(); resAvg.setNom("AVG");
                Resolution resMax = new Resolution(); resMax.setNom("MAX");
                resolutionRepository.saveAll(java.util.List.of(resMin, resAvg, resMax));

                // Dynamic Parameters for Math
                Parametre p1 = new Parametre();
                p1.setMatiere(m1);
                p1.setResolution(resMin);
                p1.setGap(2); // If total gap <= 2 -> MIN
                
                Parametre p2 = new Parametre();
                p2.setMatiere(m1);
                p2.setResolution(resAvg);
                p2.setGap(5); // If total gap <= 5 -> AVG

                Parametre p3 = new Parametre();
                p3.setMatiere(m1);
                p3.setResolution(resMax);
                p3.setGap(100); // Else (fallback or large gap) -> MAX
                
                parametreRepository.saveAll(java.util.List.of(p1, p2, p3));
            }
        };
    }
}
