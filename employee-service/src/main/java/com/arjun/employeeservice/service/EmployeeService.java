package com.arjun.employeeservice.service;

import com.arjun.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employrrDto);

    EmployeeDto getEmployeeById(Long employeeId);

}
