package com.codecool.hogwarts_potions.controller;

import com.codecool.hogwarts_potions.dto.IngredientDTO;
import com.codecool.hogwarts_potions.dto.PotionDTO;
import com.codecool.hogwarts_potions.dto.RecipeDTO;
import com.codecool.hogwarts_potions.model.*;
import com.codecool.hogwarts_potions.service.PotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/potions")
public class PotionController {


    PotionService potionService;

    @Autowired
    public PotionController(PotionService potionService) {
        this.potionService = potionService;
    }


    @GetMapping
    public List<Potion> getAllPotions() {
        return potionService.getAllPotions();
    }

    @PostMapping("/{studentId}")
    public void addPotion(@RequestBody PotionDTO potion, @PathVariable Long studentId) {
        potionService.addPotion(potion, studentId);
    }

    @GetMapping("/{studentId}")
    public List<Potion> getPotionsById(@PathVariable Long studentId) {
        return potionService.getPotionsByStudentId(studentId);
    }

    @PostMapping("/brew/{studentId}")
    public Potion brewPotion(@RequestBody PotionDTO potionDTO, @PathVariable Long studentId) {

        return potionService.createPotionAndReturn(potionDTO, studentId);

    }

    @PutMapping("/{potionId}")
    public void addIngredient(@PathVariable Long potionId, @RequestBody IngredientDTO ingredientDTO){
        potionService.addIngredientToPotion(potionId,ingredientDTO);
    }

    @GetMapping("/{potionID}/help")
    public List<Recipe> getHelp(@PathVariable Long potionID){
        return potionService.getHelp(potionID);
    }
}



