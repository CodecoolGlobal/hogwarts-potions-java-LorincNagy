package com.codecool.hogwarts_potions.controller;

import com.codecool.hogwarts_potions.model.Recipe;
import com.codecool.hogwarts_potions.service.IngredientService;
import com.codecool.hogwarts_potions.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipeService recipeService;
    private IngredientService ingredientService;

    @Autowired
    public RecipeController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<Recipe> getAllRecipe() {
        return recipeService.getAllRecipes();
    }
}