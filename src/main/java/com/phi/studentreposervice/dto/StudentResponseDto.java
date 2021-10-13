package com.phi.studentreposervice.dto;

import com.phi.studentreposervice.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {

   private Student student;


}
