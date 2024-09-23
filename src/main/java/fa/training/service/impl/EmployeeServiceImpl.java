package fa.training.service.impl;

import fa.training.dto.employee.EmployeeDto;
import fa.training.entity.Account;
import fa.training.entity.Employee;
import fa.training.mapper.account.AccountMapper;
import fa.training.mapper.employee.EmployeeMapper;
import fa.training.repository.AccountRepository;
import fa.training.repository.DepartmentRepository;
import fa.training.repository.EmployeeRepository;
import fa.training.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, DepartmentRepository departmentRepository, AccountRepository accountRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.departmentRepository = departmentRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            throw new IllegalStateException("Employee should be not null");
        }
        Employee employee = EmployeeMapper.toEntity(employeeDto);
        employee.setDepartment(departmentRepository.findById(employeeDto.getDepartment().getId()).orElseThrow(
                () -> new IllegalStateException("Get department Fail !")
        ));

        if (employeeDto.getAccountDto() == null){
            throw new IllegalStateException("Account info not null");
        }

        Account account = AccountMapper.toEntity(employeeDto.getAccountDto());
        account.setEmployee(employee);

        employee.setAccount(account);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toDto(savedEmployee);
    }

    @Override
    public Page<EmployeeDto> pagingAndSorting(String keyword, PageRequest pageable) {
        Specification<Employee> spec = (root, query, criteriaBuilder) -> {
            if (keyword == null) {
                return null;
            }

            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("firstName"), "%" + keyword + "%"),
                    criteriaBuilder.like(root.get("lastName"), "%" + keyword + "%"));
        };
        var employees = employeeRepository.findAll(spec,pageable);

        return employees.map(EmployeeMapper::toDto);
    }

    @Override
    public EmployeeDto getById(UUID id) {
        return EmployeeMapper.toDto(employeeRepository.findById(id).orElseThrow(()-> new IllegalStateException("No exist entity")));
    }

    @Override
    public EmployeeDto updateById(UUID id, EmployeeDto employeeDto) {
        if (id == null){
            throw new IllegalStateException("Id not exist!");
        }

        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new IllegalStateException("Entity with this id is not exist!"));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDob(employeeDto.getDob());
        employee.setAddress(employeeDto.getAddress());
        employee.setPhone(employeeDto.getPhone());
        employee.setDepartment(employeeDto.getDepartment());

        return EmployeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmp(UUID uuid) {
        employeeRepository.deleteById(uuid);
    }

    @Override
    public long countAllEmployees() {
        return employeeRepository.findAll().size();
    }

}
