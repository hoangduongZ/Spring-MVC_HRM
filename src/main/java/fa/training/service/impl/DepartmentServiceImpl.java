package fa.training.service.impl;

import fa.training.dto.department.DepartmentDto;
import fa.training.entity.Department;
import fa.training.repository.DepartmentRepository;
import fa.training.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream().map(department ->
                modelMapper.map(department, DepartmentDto.class)).toList();
    }

    @Override
    public long countAllDepartments() {
        return departmentRepository.findAll().size();
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = new Department(departmentDto.getId(), departmentDto.getName());
        Department savedDepartment = departmentRepository.save(department);
        return new DepartmentDto(savedDepartment.getId(), savedDepartment.getName());
    }
}
