package com.example.main.student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

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

    public Student getStudent(Long studentId){
        Boolean existe = studentRepository.existsById(studentId);

        if(!existe){
            throw new IllegalStateException("Usuario nao existe");
        }

        return studentRepository.findById(studentId).get();
    }

    public void createStudent(Student student){
        System.out.println(student.getId() + " " + student.getName());
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId, String studentName){
        Boolean existe = studentRepository.existsById(studentId);

        if(!existe){
            throw new IllegalStateException("Usuario nao existe");
        }

        if (studentName != null){
            Optional<Student> student = studentRepository.findById(studentId);
            student.get().setName(studentName);
        }
    }

    public void deleteStudent(Long studentId){
        Boolean existe = studentRepository.existsById(studentId);

        if(!existe){
            throw new IllegalStateException("Usuario nao existe");
        }

        studentRepository.deleteById(studentId);
    }

    // public Student getStudent(){
	// 	return new Student(1L,
	// 	"Henrique",
	// 	"henrique-sa@hotmail.com",
	// 	LocalDate.of(2000, 11, 12),
	// 	30);
	// }
}