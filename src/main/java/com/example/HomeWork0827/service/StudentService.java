package com.example.HomeWork0827.service;

import com.example.HomeWork0827.model.Faculty;
import com.example.HomeWork0827.model.Student;

import java.util.Collection;

public interface StudentService {
    Student addStudent (Student student);
    Student findStudent (long id);

    Collection<Student> getByAge(Integer min, Integer max);

    Student editStudent (long id, Student student);
    void deleteStudent (long id);

    Faculty findFaculty(String name);
}