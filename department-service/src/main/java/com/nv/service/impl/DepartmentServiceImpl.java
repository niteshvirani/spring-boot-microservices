package com.nv.service.impl;

import com.nv.client.EmployeeServiceClient;
import com.nv.dto.DepartmentDto;
import com.nv.entity.Department;
import com.nv.exception.ResourceNotFoundException;
import com.nv.repository.DepartmentRepository;
import com.nv.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeServiceClient employeeServiceClient;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeServiceClient employeeServiceClient) {
        this.departmentRepository = departmentRepository;
        this.employeeServiceClient = employeeServiceClient;
    }

    @Override
    public DepartmentDto create(DepartmentDto departmentDto) {
        Department department = DepartmentDto.fromDto(departmentDto);
        departmentRepository.save(department);
        return DepartmentDto.fromEntity(department);
    }

    @Override
    public List<DepartmentDto> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(DepartmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto findById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exists with given id: " + id));
        return DepartmentDto.fromEntity(department);
    }

    @Override
    public List<DepartmentDto> findAllWithEmployees() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(DepartmentDto::fromEntity)
                .peek(departmentDto -> departmentDto.setEmployees(
                        employeeServiceClient.findByDepartmentId(departmentDto.getId())))
                .collect(Collectors.toList());
    }
}
