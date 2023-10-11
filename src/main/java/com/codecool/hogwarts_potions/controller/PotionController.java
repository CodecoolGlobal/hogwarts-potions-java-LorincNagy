package com.codecool.hogwarts_potions.controller;

import com.codecool.hogwarts_potions.dto.PotionDTO;
import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Potion;
import com.codecool.hogwarts_potions.model.Room;
import com.codecool.hogwarts_potions.model.Student;
import com.codecool.hogwarts_potions.service.PotionService;
import org.springframework.beans.factory.annotation.Autowired;
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


//    @GetMapping
//    public List<Potion> getAllPotions() {
//        return potionService.getAllPotions();
//    }

//    @PostMapping
//    public void addPotion(@RequestBody PotionDTO potionDTO) {
//        // Hozz létre egy Potion entitást a PotionDTO alapján
//        Potion potion = new Potion();
//        potion.setName(potionDTO.name());
//        potion.setStudent(potionDTO.student());
//        potion.setIngredients(potionDTO.ingredients());
////            for (Ingredient ingredient : potionDTO.ingredients()) {
////
////                potion.getIngredients().add(ingredient);
////            }
//
//        potion.setBrewingStatus(potionDTO.brewingStatus());
//        potion.setRecipe(potionDTO.recipe());
//
//        // Mentés az adatbázisba
//        potionService.addPotion(potion);
//    }
}



