package com.codecool.hogwarts_potions.dto;

import com.codecool.hogwarts_potions.model.BrewingStatus;
import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Recipe;
import com.codecool.hogwarts_potions.model.Student;

import java.util.List;
import java.util.Set;

public record PotionDTO(
        String name,
        Student student,
        Set<Ingredient>ingredients,
        BrewingStatus brewingStatus,
        Recipe recipe
) {
}
