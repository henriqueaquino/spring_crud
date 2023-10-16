package com.example.main.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping (path = "{studentId}")
	public Student getStudent(
        @PathVariable("studentId") Long studentId
    ){
        return studentService.getStudent(studentId);
    }

    @GetMapping(path = "/create")
    public void createStudent(){
        studentService.createStudent(new Student("Henrique"));
        studentService.createStudent(new Student("Sergio"));
        studentService.createStudent(new Student("Aquino"));
    }

    @PostMapping
    public void createStudent(
        @RequestBody Student student
    ){
        System.out.println(student.getId() + " " + student.getName());
        studentService.createStudent(student);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
        @PathVariable("studentId") Long studentId,
        @RequestParam(required = false) String name
        //@RequestParam(value = "name", defaultValue = "World") String name
    ){
        studentService.updateStudent(studentId, name);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(
        @PathVariable("studentId") Long studentId
    ){
        studentService.deleteStudent(studentId);
    }
}