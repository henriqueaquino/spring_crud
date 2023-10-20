package com.example.main.student;

import java.time.LocalDate;
import java.util.List;

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

    public Iterable<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudent(Long studentId){
        Boolean existe = studentRepository.existsById(studentId);

        if(!existe){
            throw new IllegalStateException("Usuario nao existe");
        }

        return studentRepository.findById(studentId).get();
    }

    public Student createStudent(Student student){
        System.out.println(student.getId() + " " + student.getName());
        Student newStudent = studentRepository.save(student);
        return newStudent;
    }

    // @Transactional
    // public void updateStudent(Long studentId, String studentName){
    //     Boolean existe = studentRepository.existsById(studentId);

    //     if(!existe){
    //         throw new IllegalStateException("Usuario nao existe");
    //     }

    //     if (studentName != null){
    //         Optional<Student> student = studentRepository.findById(studentId);
    //         student.get().setName(studentName);
    //     }
    // }

    @Transactional
    public void updateStudent(Student student, Long studentId){
        System.out.println(student.getName() + " " + student.getId());
        Boolean existe = studentRepository.existsById(studentId);
        
        if(!existe){
            throw new IllegalStateException("Usuario nao existe");
        }

        Student newStudent = studentRepository.findById(studentId).get();
        newStudent.setName(student.getName());
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