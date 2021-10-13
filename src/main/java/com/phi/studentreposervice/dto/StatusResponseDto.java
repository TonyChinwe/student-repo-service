package com.phi.studentreposervice.dto;

import com.phi.studentreposervice.utility.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponseDto {
    private Status status;
}
