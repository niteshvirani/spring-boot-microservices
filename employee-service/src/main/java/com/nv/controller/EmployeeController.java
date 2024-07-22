package com.nv.controller;

import com.nv.dto.EmployeeDto;
import com.nv.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1 = employeeService.create(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto1);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        List<EmployeeDto> employeeDtos = employeeService.findAll();
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeService.findById(id);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("department/{departmentId}")
    public ResponseEntity<List<EmployeeDto>> findByDepartmentId(@PathVariable Long departmentId) {
        List<EmployeeDto> employeeDtos = employeeService.findByDepartmentId(departmentId);
        return ResponseEntity.ok(employeeDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id,
                                              @RequestBody EmployeeDto updatedEmployee) {
        EmployeeDto employeeDto = employeeService.update(id, updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }
}
