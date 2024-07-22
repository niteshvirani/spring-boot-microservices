package com.nv.dto;

import com.nv.entity.Employee;
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

    public static EmployeeDto fromEntity(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .departmentId(employee.getDepartmentId())
                .build();
    }

    public static Employee fromDto(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .departmentId(employeeDto.getDepartmentId())
                .build();
    }
}
