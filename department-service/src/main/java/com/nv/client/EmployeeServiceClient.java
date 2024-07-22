package com.nv.client;

import com.nv.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "employee-service")
public interface EmployeeServiceClient {

    @GetMapping("/v1/api/employees/department/{departmentId}")
    List<EmployeeDto> findByDepartmentId(@PathVariable Long departmentId);
}
