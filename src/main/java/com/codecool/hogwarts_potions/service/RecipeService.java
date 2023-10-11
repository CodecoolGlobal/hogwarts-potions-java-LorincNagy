package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.RecipeRepository;
import com.codecool.hogwarts_potions.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public void addRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }
}
