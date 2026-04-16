package com.forrage.app.repository;

import com.forrage.app.model.DetailDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailDevisRepository extends JpaRepository<DetailDevis, Long> {
}
