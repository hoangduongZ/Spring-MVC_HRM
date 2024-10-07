package fa.training.service;

import fa.training.dto.employee.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface EmployeeService {
    EmployeeDto save(EmployeeDto employeeDto);

    Page<EmployeeDto> pagingAndSorting(String keyword, PageRequest pageable);

    EmployeeDto getById(UUID id);

    EmployeeDto updateById(UUID id, EmployeeDto employeeDto);

    void deleteEmp(UUID uuid);

    long countAllEmployees();
}
