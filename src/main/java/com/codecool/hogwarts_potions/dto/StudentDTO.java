package com.codecool.hogwarts_potions.dto;

import com.codecool.hogwarts_potions.model.HouseType;
import com.codecool.hogwarts_potions.model.PetType;

public record StudentDTO(
        String name,
        HouseType houseType,
        PetType petType
) {
}