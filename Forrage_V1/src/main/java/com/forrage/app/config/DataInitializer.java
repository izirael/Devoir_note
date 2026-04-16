package com.forrage.app.config;

import com.forrage.app.model.StatutDevis;
import com.forrage.app.model.TypeDevis;
import com.forrage.app.repository.StatutRepository;
import com.forrage.app.repository.TypeDevisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final StatutRepository repository;
    private final TypeDevisRepository typeDevisRepository;

    @Override
    public void run(String... args) throws Exception {
        // Init StatutDevis
        saveStatutIfMissing("Nouveau");
        saveStatutIfMissing("Fait");
        saveStatutIfMissing("Devis Etude Créé");
        saveStatutIfMissing("Devis Forage Créé");
        saveStatutIfMissing("Devis Groupé Créé");

        // Init TypeDevis
        saveTypeDevisIfMissing("Etude");
        saveTypeDevisIfMissing("Forage");
    }

    private void saveStatutIfMissing(String nom) {
        if (repository.findByNom(nom).isEmpty()) {
            StatutDevis s = new StatutDevis();
            s.setNom(nom);
            repository.save(s);
        }
    }

    private void saveTypeDevisIfMissing(String nom) {
        if (typeDevisRepository.findByNom(nom).isEmpty()) {
            TypeDevis t = new TypeDevis();
            t.setNom(nom);
            typeDevisRepository.save(t);
        }
    }
}
