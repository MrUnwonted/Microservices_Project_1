package com.arjun.departmentservice.service;

import com.arjun.departmentservice.dto.DepartmentDto;
import com.arjun.departmentservice.entity.Department;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);
}
