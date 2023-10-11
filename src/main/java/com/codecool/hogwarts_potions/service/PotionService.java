package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.PotionRepository;
import com.codecool.hogwarts_potions.model.Potion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotionService {

    private final PotionRepository potionRepository;

    @Autowired
    public PotionService(PotionRepository potionRepository) {
        this.potionRepository = potionRepository;
    }

    public List<Potion> getAllPotions() {
        return potionRepository.findAll();
    }


    public void addPotion(Potion potion) {
        potionRepository.save(potion);
    }
}
