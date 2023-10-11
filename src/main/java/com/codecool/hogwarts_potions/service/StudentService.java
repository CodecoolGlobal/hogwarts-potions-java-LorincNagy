package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.StudentRepository;
import com.codecool.hogwarts_potions.dto.StudentDTO;
import com.codecool.hogwarts_potions.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Autowired
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.name());
        student.setHouseType(studentDTO.houseType());
        student.setPetType(studentDTO.petType());


        studentRepository.save(student);
    }
}
