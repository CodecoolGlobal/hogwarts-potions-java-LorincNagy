package com.codecool.hogwarts_potions.dto;

import com.codecool.hogwarts_potions.model.Student;

import java.util.Set;

public record RoomDTO(Integer capacity, Set<Student> residents) {
}