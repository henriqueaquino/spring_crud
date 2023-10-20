package com.example.main.student;

import java.net.URI;
import java.net.http.*;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {
    
    private StudentService studentService;

    public StudentController(){

    }

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
	public ResponseEntity<Iterable<Student>> getStudents(){
        return ResponseEntity.ok(studentService.getStudents());
    }

    // @GetMapping (path = "{studentId}")
	// public Student getStudent(
    //     @PathVariable("studentId") Long studentId
    // ){
    //     return studentService.getStudent(studentId);
    // }

    @GetMapping (path = "{studentId}")
	public ResponseEntity<Student> getStudent(
        @PathVariable("studentId") Long studentId
    ){
        Student student = studentService.getStudent(studentId);
        return ResponseEntity.ok(student);
    }

    @GetMapping(path = "/create")
    public void createStudent(){
        studentService.createStudent(new Student("Henrique"));
        studentService.createStudent(new Student("Sergio"));
        studentService.createStudent(new Student("Aquino"));
    }

    // @PostMapping
    // public void createStudent(
    //     @RequestBody Student student
    // ){
    //     studentService.createStudent(student);
    // }

    @PostMapping
    public ResponseEntity<Void> createStudent(
        @RequestBody Student student
    ){
        Student newStudent = studentService.createStudent(student);
        URI locationOfNewStudent = URI.create("api/student/" + newStudent.getId());
        return ResponseEntity
        .created(locationOfNewStudent)
        .build();
    }

    // @PutMapping(path = "{studentId}")
    // public void updateStudent(
    //     @PathVariable("studentId") Long studentId,
    //     @RequestParam(required = false) String name
    //     //@RequestParam(value = "name", defaultValue = "World") String name
    // ){
    //     studentService.updateStudent(studentId, name);
    // }

    // @PutMapping(path = "{studentId}")
    // public void updateStudent(
    //     @RequestBody Student student,
    //     @PathVariable Long studentId
    // ){
    //     studentService.updateStudent(student, studentId);
    // }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<Void> updateStudent(
        @RequestBody Student student,
        @PathVariable Long studentId
    ){
        studentService.updateStudent(student, studentId);
        return ResponseEntity.noContent().build();
    }

    // @DeleteMapping(path = "{studentId}")
    // public void deleteStudent(
    //     @PathVariable("studentId") Long studentId
    // ){
    //     studentService.deleteStudent(studentId);
    // }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<Void> deleteStudent(
        @PathVariable("studentId") Long studentId
    ){
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}