package com.nv.service;

import com.nv.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employeeDto);

    List<EmployeeDto> findAll();

    EmployeeDto findById(Long id);

    List<EmployeeDto> findByDepartmentId(Long departmentId);

    EmployeeDto update(Long employeeId, EmployeeDto updatedEmployee);

    Boolean delete(Long employeeId);
}
