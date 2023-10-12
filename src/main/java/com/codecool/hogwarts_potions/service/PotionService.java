package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.IngredientRepository;
import com.codecool.hogwarts_potions.dao.PotionRepository;
import com.codecool.hogwarts_potions.dao.RecipeRepository;
import com.codecool.hogwarts_potions.dao.StudentRepository;
import com.codecool.hogwarts_potions.dto.IngredientDTO;
import com.codecool.hogwarts_potions.dto.PotionDTO;
import com.codecool.hogwarts_potions.dto.RecipeDTO;
import com.codecool.hogwarts_potions.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

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
        return potionList;
    }

    public Potion createPotionAndReturn(PotionDTO potionDTO, Long studentId) {
        // Diák lekérése az adatbázisból a studentId alapján (feltételezve, hogy van Student entitás)
        Student student = studentRepository.findById(studentId).get();

        // Létrehozzuk az új Potion objektumot a kapott DTO alapján
        Potion potion = new Potion();
        potion.setName(potionDTO.name()); // A beérkező DTO-ból kiolvassuk a nevet
        potion.setBrewingStatus(BrewingStatus.BREW); // Főzés alatt státusz beállítása
        potion.setStudent(student); // A diák hozzárendelése az italhoz
        student.addPotion(potion);
        // Ital mentése az adatbázisban
        potionRepository.save(potion);
        return potion;

        // Az ital adatainak átmásolása egy új PotionDTO-be
//        PotionDTO createdPotionDTO = new PotionDTO();
//        createdPotionDTO.setId(potion.getId());
//        createdPotionDTO.setName(potion.getName());
//        createdPotionDTO.setBrewingStatus(potion.getBrewingStatus());
//        createdPotionDTO.setStudent(potion.getStudent());
//
//        return createdPotionDTO;
    }

    public void addIngredientToPotion(Long potionId, IngredientDTO ingredientDTO) {
        // Lekérjük az italt az adatbázisból a potionId alapján
        Potion potion = potionRepository.findById(potionId).get();


        // Létrehozzuk az új összetevőt az IngredientDTO alapján
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.name());
        // További adatok beállítása az Ingredient objektumra szükség szerint

        // Hozzáadjuk az összetevőt az italhoz
        potion.addIngredient(ingredient);

        // Az ital frissítése az adatbázisban
        potionRepository.save(potion);
    }

    public List<Recipe> getHelp(Long potionID) {
        // Lekérjük az italt az adatbázisból a potionID alapján
        Potion potion = potionRepository.findById(potionID).get();


        // Ellenőrizzük, hogy az italnak kevesebb, mint öt összetevője van
        if (potion.getIngredients().size() < 5) {
            // Lekérjük az összetevők listáját az italból
            Set<Ingredient> ingredients = potion.getIngredients();

            // Létrehozzuk egy üres receptek listát
            List<Recipe> matchingRecipes = new ArrayList<>();

            // Lekérjük az összes receptet az adatbázisból (feltételezve, hogy van RecipeRepository)
            List<Recipe> allRecipes = recipeRepository.findAll();

            // Ellenőrizzük, hogy mely receptek tartalmazzák ugyanazokat az összetevőket
            for (Recipe recipe : allRecipes) {
                if (recipe.getIngredients().containsAll(ingredients)) {
                    matchingRecipes.add(recipe);
                }
            }

            // Visszaadjuk a megegyező receptek listáját
            return matchingRecipes;
        } else {
            // Ha az italnak már 5 vagy több összetevője van, nem kell segítség, üres lista
            return Collections.emptyList();
        }
    }
}
