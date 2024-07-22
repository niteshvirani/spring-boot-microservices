package com.nv.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmployeeDto {
    private Long id;
    private String name;
    private Long departmentId;
}
