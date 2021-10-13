package com.phi.studentreposervice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phi.studentreposervice.entities.Student;
import com.phi.studentreposervice.service.StudentService;
import com.phi.studentreposervice.utility.Status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private StudentService studentService;


    @Test
    void getStudents() throws Exception{
        Student studentOne = new Student( 1L, "Tony",100L);
        Student  studentTwo = new Student( 2L,"Chinwe", 200L);
        Student  studentThree = new Student( 3L, "Frank", 300L);
        List<Student> students = Arrays.asList(studentOne, studentTwo, studentThree);
        given(studentService.getAllStudents()).willReturn(students);
        mvc.perform(get("/api/v1/student")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(studentOne.getName())))
                .andExpect(jsonPath("$[1].name", is(studentTwo.getName())));
        verify(studentService, VerificationModeFactory.times(1)).getAllStudents();
        reset(studentService);

    }

    @Test
    void getStudentById()throws Exception {
        Optional<Student> studentOne = Optional.of(new Student(1L,"Tony", 100L));
        given(studentService.findStudentById(Mockito.anyLong())).willReturn(studentOne);
        mvc.perform(get("/api/v1/student/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(studentOne.get().getName())));
        verify(studentService, VerificationModeFactory.times(1)).findStudentById(Mockito.anyLong());
        reset(studentService);

    }

    @Test
    void getStudentByName()throws Exception {

        Optional<Student> studentOne = Optional.of(new Student(1L,"Tony", 100L));
        given(studentService.findStudentByName(Mockito.anyString())).willReturn(studentOne);
        mvc.perform(get("/api/v1/student/name/Tony")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(studentOne.get().getName())));
        verify(studentService, VerificationModeFactory.times(1)).findStudentByName(Mockito.anyString());
        reset(studentService);
    }

    @Test
    void getStudentByRegNumber()throws Exception {

        Optional<Student> studentOne = Optional.of(new Student(1L,"Tony", 100L));
        given(studentService.findStudentByRegNumber(Mockito.anyLong())).willReturn(studentOne);
        mvc.perform(get("/api/v1/student/number/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(studentOne.get().getName())));
        verify(studentService, VerificationModeFactory.times(1)).findStudentByRegNumber(Mockito.anyLong());
        reset(studentService);
    }

    @Test
    void addStudent()throws Exception {

        Status statusOne =new Status(200,"Student added successfully");
        given(studentService.saveStudent(Mockito.any())).willReturn(statusOne);
        mvc.perform(post("/api/v1/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(statusOne))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode", is(statusOne.getStatusCode())));
        verify(studentService, VerificationModeFactory.times(1)).saveStudent(Mockito.any());
        reset(studentService);
    }

    @Test
    void updateStudent()throws Exception {

        Status statusOne =new Status(200,"Student updated successfully");
        given(studentService.updateStudent(Mockito.any())).willReturn(statusOne);
        mvc.perform(put("/api/v1/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(statusOne))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode", is(statusOne.getStatusCode())));
        verify(studentService, VerificationModeFactory.times(1)).updateStudent(Mockito.any());
        reset(studentService);
    }

    @Test
    void removeStudent() throws Exception {
        Status statusOne =new Status(200,"Student removed successfully");
        given(studentService.removeStudent(Mockito.anyLong())).willReturn(statusOne);
        mvc.perform(delete("/api/v1/student/remove/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode", is(statusOne.getStatusCode())));
        verify(studentService, VerificationModeFactory.times(1)).removeStudent(Mockito.anyLong());
        reset(studentService);

    }
}