package com.codecool.hogwarts_potions.dao;

import com.codecool.hogwarts_potions.model.Potion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PotionRepository extends JpaRepository<Potion, Long> {
    List<Potion> getPotionsByStudentId(Long studentId);
}
