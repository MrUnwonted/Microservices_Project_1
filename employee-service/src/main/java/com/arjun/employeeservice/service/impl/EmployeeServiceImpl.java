package com.arjun.employeeservice.service.impl;

import com.arjun.employeeservice.dto.APIResponseDto;
import com.arjun.employeeservice.dto.DepartmentDto;
import com.arjun.employeeservice.dto.EmployeeDto;
import com.arjun.employeeservice.entity.Employee;
import com.arjun.employeeservice.repository.EmployeeRepository;
import com.arjun.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private RestTemplate  restTemplate;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employrrDto) {

        Employee employee = new Employee(
                employrrDto.getId(),
                employrrDto.getFirstName(),
                employrrDto.getLastName(),
                employrrDto.getEmail(),
                employrrDto.getDepartmentCode()
        );

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );


        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://DEPARTMENT-SERVICE/api/departments/" + employee.getDepartmentCode(),
                DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);


        return apiResponseDto;
    }
}
