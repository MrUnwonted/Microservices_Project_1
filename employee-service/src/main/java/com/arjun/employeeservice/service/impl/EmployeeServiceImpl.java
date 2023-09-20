package com.arjun.employeeservice.service.impl;

import com.arjun.employeeservice.dto.APIResponseDto;
import com.arjun.employeeservice.dto.DepartmentDto;
import com.arjun.employeeservice.dto.EmployeeDto;
import com.arjun.employeeservice.dto.OrganizationDto;
import com.arjun.employeeservice.entity.Employee;
import com.arjun.employeeservice.mapper.EmployeeMapper;
import com.arjun.employeeservice.repository.EmployeeRepository;
import com.arjun.employeeservice.service.APIClient;
import com.arjun.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    private EmployeeRepository employeeRepository;
//    private RestTemplate  restTemplate;
    private WebClient webClient;

    private APIClient apiClient;



    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(employee);


        return savedEmployeeDto;
    }

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "{spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(employeeId).get();

//        Using REST Template client method
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8082/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

        //        Using WebFlux dependency method
      DepartmentDto departmentDto =  webClient.get()
                .uri("http://localhost:8082/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }


    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception){

        LOGGER.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId).get();

     DepartmentDto departmentDto = new DepartmentDto();
     departmentDto.setDepartmentName("R&D Department");
     departmentDto.setDepartmentCode("RD001");
     departmentDto.setDepartmentDescription("Research and Development Department");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);


        return apiResponseDto;
    }


}
