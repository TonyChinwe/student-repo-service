package com.phi.studentreposervice.controller;

import com.phi.studentreposervice.dto.StatusResponseDto;
import com.phi.studentreposervice.dto.StudentListDto;
import com.phi.studentreposervice.dto.StudentResponseDto;
import com.phi.studentreposervice.entities.Student;
import com.phi.studentreposervice.service.StudentService;
import com.phi.studentreposervice.utility.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {


    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public StudentListDto getStudents() {
        StudentListDto studentListDto =new StudentListDto();
        studentListDto.setStudentList(studentService.getAllStudents());
        return studentListDto;
    }

    @GetMapping("/id/{studentId}")
    public StudentResponseDto getStudentById(@PathVariable Long studentId) {
        Optional<Student> student=studentService.findStudentById(studentId);
        StudentResponseDto studentResponseDto=new StudentResponseDto();
        if(student.isPresent()){
            studentResponseDto.setStudent(student.get());
            return studentResponseDto;
        }else {
            return studentResponseDto;
        }
    }

    @GetMapping("/name/{name}")
    public StudentResponseDto getStudentByName(@PathVariable String name) {
        Optional<Student> student=studentService.findStudentByName(name);
        StudentResponseDto studentResponseDto=new StudentResponseDto();
        if(student.isPresent()){
            studentResponseDto.setStudent(student.get());
            return studentResponseDto;
        }else {
            return studentResponseDto;
        }
    }

    @GetMapping("/number/{number}")
    public StudentResponseDto getStudentByRegNumber(@PathVariable Long number) {
        Optional<Student> student=studentService.findStudentByRegNumber(number);
        StudentResponseDto studentResponseDto=new StudentResponseDto();
        if(student.isPresent()){
            studentResponseDto.setStudent(student.get());
            return studentResponseDto;
        }else {
            return studentResponseDto;
        }
    }

    @PostMapping
    public StatusResponseDto addStudent(@RequestBody Student student) {
        Status status=studentService.saveStudent(student);
        StatusResponseDto statusResponseDto=new StatusResponseDto();
        statusResponseDto.setStatus(status);
        return statusResponseDto;

    }

    @PutMapping
    public StatusResponseDto updateStudent(@RequestBody Student student) {
        Status status=studentService.updateStudent(student);
       StatusResponseDto statusResponseDto=new StatusResponseDto();
       statusResponseDto.setStatus(status);
       return statusResponseDto;
    }

    @DeleteMapping("/remove/{studentId}")
    public StatusResponseDto removeStudent(@PathVariable Long studentId) {
        Status status=studentService.removeStudent(studentId);
        StatusResponseDto statusResponseDto=new StatusResponseDto();
        statusResponseDto.setStatus(status);
        return statusResponseDto;
    }


}
