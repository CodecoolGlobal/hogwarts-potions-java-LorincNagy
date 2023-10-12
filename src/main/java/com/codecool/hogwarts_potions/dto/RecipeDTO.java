package com.codecool.hogwarts_potions.dto;

import com.codecool.hogwarts_potions.model.Student;

import java.util.List;

public record RecipeDTO( String name, Student brewer, List<IngredientDTO> ingredients) {
}
