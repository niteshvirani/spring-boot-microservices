package com.nv.service.impl;

import com.nv.dto.EmployeeDto;
import com.nv.entity.Employee;
import com.nv.exception.ResourceNotFoundException;
import com.nv.producer.KafkaMessageProducer;
import com.nv.repository.EmployeeRepository;
import com.nv.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final KafkaMessageProducer kafkaMessageProducer;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, KafkaMessageProducer kafkaMessageProducer) {
        this.employeeRepository = employeeRepository;
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        Employee employee = EmployeeDto.fromDto(employeeDto);
        employeeRepository.save(employee);
        employeeDto = EmployeeDto.fromEntity(employee);
        kafkaMessageProducer.publishEmployeeCreated(employeeDto);
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(Long id) {
        Employee employeeOptional = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with given id: " + id));
        return EmployeeDto.fromEntity(employeeOptional);
    }

    @Override
    public List<EmployeeDto> findByDepartmentId(Long departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employees.stream()
                .map(EmployeeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto update(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with given id: " + employeeId));

        employee.setName(updatedEmployee.getName());
        employee.setDepartmentId(updatedEmployee.getDepartmentId());

        employeeRepository.save(employee);

        return EmployeeDto.fromEntity(employee);
    }

    @Override
    public Boolean delete(Long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with given id: " + employeeId));

        employeeRepository.deleteById(employeeId);

        return Boolean.TRUE;
    }
}
