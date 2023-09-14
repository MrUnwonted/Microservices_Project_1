package com.arjun.employeeservice.service;

import com.arjun.employeeservice.dto.APIResponseDto;
import com.arjun.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employrrDto);

    APIResponseDto getEmployeeById(Long employeeId);

}
