package com.example.notev2.repository;

import com.example.notev2.model.Note;
import com.example.notev2.model.Candidat;
import com.example.notev2.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByCandidatAndMatiere(Candidat candidat, Matiere matiere);
}
