package com.arjun.departmentservice.service.impl;

import com.arjun.departmentservice.dto.DepartmentDto;
import com.arjun.departmentservice.entity.Department;
import com.arjun.departmentservice.mapper.DepartmentMapper;
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

     Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto saveDepartmentDto = DepartmentMapper.mapToDepartmentDto(department);

        return saveDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {

        Department department = departmentRepository.findByDepartmentCode(code);

        DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);

        return departmentDto;
    }
}
