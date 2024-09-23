package fa.training.service;

import fa.training.dto.department.DepartmentDto;

import java.util.List;

public interface DepartmentService{
    List<DepartmentDto> findAll();

    long countAllDepartments();

    DepartmentDto addDepartment(DepartmentDto departmentDto);
}
