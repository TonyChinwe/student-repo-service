package com.phi.studentreposervice.service;

import com.phi.studentreposervice.entities.Student;
import com.phi.studentreposervice.repository.StudentRepository;
import com.phi.studentreposervice.utility.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentById(Long studentId){
        return studentRepository.findById(studentId);
    }

    public Optional<Student> findStudentByName(String name){
        return studentRepository.findStudentByName(name);
    }

    public Optional<Student> findStudentByRegNumber(Long number){
        return studentRepository.findStudentByRegNumber(number);
    }


    public Status saveStudent(Student student){

    Optional<Student> studentWithDublicateName=findStudentByName(student.getName());
                 if(studentWithDublicateName.isPresent()){
                     return new Status(400,"Student with the same name already exist");

                 }
    Optional<Student> studentWithDublicateNumber=findStudentByRegNumber(student.getRegNumber());
                 if(studentWithDublicateNumber.isPresent()){
                     return new Status(400,"Student with the same number already exist");
                 }
     return  validateStudentData(student);
    }

    public Status updateStudent(Student student){
        Optional<Student>studentToUpdate=studentRepository.findById(student.getId());
        if(studentToUpdate.isPresent()){
            String studentName=studentToUpdate.get().getName();
            Long studentNumber=studentToUpdate.get().getRegNumber();
            studentToUpdate.get().setName(student.getName()==null||student.getName().isEmpty()?studentName:student.getName());
            studentToUpdate.get().setRegNumber(student.getRegNumber()==null?studentNumber:student.getRegNumber());
            studentRepository.save(studentToUpdate.get());
            return new Status(200,"Student Updated Successfully");

        }
        return new Status(400,"Student with the id does not exist");
    }


    public Status removeStudent(Long studentId) {
        Optional<Student> student=studentRepository.findById(studentId);
        if(!student.isPresent()){
            return new Status(404,"The Request Failed. The Student Do Not Exist ");
        }
        studentRepository.deleteById(studentId);
        return new Status(201,"Student Was Successfully Removed");
    }

    private Status validateStudentData(Student student){
        if(student.getName()==null||student.getName().isEmpty()){
            return new Status(400,"Missing Student's Name ");
        }
        if(student.getRegNumber()==null||student.getRegNumber()<0){
            return new Status(400,"Missing or Bad Student's Registration Number");
        }
        studentRepository.save(student);
        return new Status(200,"Student Saved Successfully");
    }

}
