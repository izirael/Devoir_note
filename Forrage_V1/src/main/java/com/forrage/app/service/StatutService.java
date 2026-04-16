package com.forrage.app.service;

import com.forrage.app.model.StatutDevis;
import com.forrage.app.repository.StatutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatutService {
    private final StatutRepository repository;

    public List<StatutDevis> findAll() {
        return repository.findAll();
    }

    public StatutDevis findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public StatutDevis save(StatutDevis statut) {
        return repository.save(statut);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    public StatutDevis findByNom(String nom) {
        return repository.findByNom(nom).orElse(null);
    }
}
