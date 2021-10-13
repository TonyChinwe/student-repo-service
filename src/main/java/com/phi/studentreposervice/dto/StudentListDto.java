package com.phi.studentreposervice.dto;


import com.phi.studentreposervice.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentListDto {
    private List<Student> studentList;
}
