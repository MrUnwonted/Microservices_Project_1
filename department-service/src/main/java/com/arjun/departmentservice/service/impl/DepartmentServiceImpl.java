package com.arjun.departmentservice.service.impl;

import com.arjun.departmentservice.dto.DepartmentDto;
import com.arjun.departmentservice.entity.Department;
import com.arjun.departmentservice.repository.DepartmentRepository;
import com.arjun.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentcode()
        );

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto saveDepartmentDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()


        );

        return saveDepartmentDto;
    }
}
