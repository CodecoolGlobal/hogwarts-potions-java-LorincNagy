package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.IngredientRepository;
import com.codecool.hogwarts_potions.dao.PotionRepository;
import com.codecool.hogwarts_potions.dao.RecipeRepository;
import com.codecool.hogwarts_potions.dao.StudentRepository;
import com.codecool.hogwarts_potions.dto.PotionDTO;
import com.codecool.hogwarts_potions.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PotionService {

    private final PotionRepository potionRepository;

    private final RecipeRepository recipeRepository;

    private final IngredientRepository ingredientRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public PotionService(PotionRepository potionRepository, RecipeRepository recipeRepository, IngredientRepository ingredientRepository, StudentRepository studentRepository) {
        this.potionRepository = potionRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.studentRepository = studentRepository;
    }

    public List<Potion> getAllPotions() {
        return potionRepository.findAll();
    }


    public void addPotion(PotionDTO potionDTO, Long studentId) {
        Student student = studentRepository.findById(studentId).get();

        // Kinyerjük a PotionDTO-ból az adatokat
        String name = potionDTO.name();
        BrewingStatus brewingStatus = potionDTO.brewingStatus();
        Set<Ingredient> ingredients = potionDTO.ingredients();

        // Létrehozzuk az új Potion objektumot és beállítjuk az adatokat
        Potion potion = new Potion();
        potion.setName(name);
        potion.setBrewingStatus(brewingStatus);
        potion.setStudent(student);
        potion.setIngredients(ingredients);

        student.addPotion(potion);
        potionRepository.save(potion);
        updateBrewingStatus(potion);
    }

    private void updateBrewingStatus(Potion potion) {
        if (potion.getIngredients() == null) {
            potion.setBrewingStatus(BrewingStatus.BREW);
            potionRepository.save(potion);
            return;
        }
        if (potion.getIngredients().size() < 5) {
            potion.setBrewingStatus(BrewingStatus.BREW);
            potionRepository.save(potion);
        } else {
            if (isRecipeExists(potion)) {
                potion.setBrewingStatus(BrewingStatus.REPLICA);
                potionRepository.save(potion);
            } else {
                potion.setBrewingStatus(BrewingStatus.DISCOVERY);
                potionRepository.save(potion);
            }
        }
    }

    private boolean isRecipeExists(Potion potion) {
        return recipeRepository.findAll().stream()
                .anyMatch(recipe -> new HashSet<>(recipe.getIngredients())
                        .containsAll(potion.getIngredients()));
    }

    public List<Potion> getPotionsByStudentId(Long studentId) {
        List<Potion> potionList = potionRepository.getPotionsByStudentId(studentId);
        return  potionList;
    }
}
