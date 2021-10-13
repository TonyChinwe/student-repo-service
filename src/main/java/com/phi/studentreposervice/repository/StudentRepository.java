package com.phi.studentreposervice.repository;

import com.phi.studentreposervice.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findStudentByName(String name);
    Optional<Student> findStudentByRegNumber(Long regNumber);


}
