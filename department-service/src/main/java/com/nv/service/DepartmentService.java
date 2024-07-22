package com.nv.service;

import com.nv.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto create(DepartmentDto departmentDto);

    List<DepartmentDto> findAll();

    DepartmentDto findById(Long id);

    List<DepartmentDto> findAllWithEmployees();
}
