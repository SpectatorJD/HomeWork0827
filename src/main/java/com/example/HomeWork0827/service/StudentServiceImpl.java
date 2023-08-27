package com.example.HomeWork0827.service;


import com.example.HomeWork0827.model.Faculty;
import com.example.HomeWork0827.model.Student;
import com.example.HomeWork0827.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService{


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent (Student student){
        return studentRepository.save(student);
    }

    public Student findStudent(long id){
        return studentRepository.findById(id).get();
    }
    @Override
    public Collection<Student> getByAge(Integer min, Integer max){
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Student editStudent(long id, Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }


    public Faculty findFaculty(String name) {
        return  studentRepository.findByName(name).getFaculty();
    }

}
