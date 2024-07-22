package com.nv.controller;

import com.nv.dto.DepartmentDto;
import com.nv.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/departments")
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> create(@RequestBody DepartmentDto departmentDto) {
        log.info("create department");
        DepartmentDto department = departmentService.create(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(department);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAll() {
        log.info("findAll department");
        List<DepartmentDto> departments = departmentService.findAll();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> findById(@PathVariable Long id) {
        log.info("findById department: {}", id);
        DepartmentDto departmentDto = departmentService.findById(id);
        return ResponseEntity.ok(departmentDto);
    }

    @GetMapping("with-employees")
    public ResponseEntity<List<DepartmentDto>> findAllWithEmployees() {
        log.info("findAllWithEmployees");
        List<DepartmentDto> departments = departmentService.findAllWithEmployees();
        return ResponseEntity.ok(departments);
    }
}
