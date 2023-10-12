package com.codecool.hogwarts_potions.controller;

import com.codecool.hogwarts_potions.dto.StudentDTO;
import com.codecool.hogwarts_potions.model.Student;
import com.codecool.hogwarts_potions.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public void addStudent(@RequestBody StudentDTO student) {
        studentService.addStudent(student);
    }
}

