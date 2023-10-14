package com.example.main.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(){

    }

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    // public Student getStudent(){
	// 	return new Student(1L,
	// 	"Henrique",
	// 	"henrique-sa@hotmail.com",
	// 	LocalDate.of(2000, 11, 12),
	// 	30);
	// }
}