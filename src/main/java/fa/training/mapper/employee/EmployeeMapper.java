package fa.training.mapper.employee;

import fa.training.dto.employee.EmployeeDto;
import fa.training.entity.Department;
import fa.training.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto toDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setPhone(employee.getPhone());
        dto.setDob(employee.getDob());
        dto.setGender(employee.getGender());
        dto.setAddress(employee.getAddress());
        dto.setRemark(employee.getRemark());

        if (employee.getDepartment() != null) {
            dto.setDepartment(employee.getDepartment());
        }

        return dto;
    }

    public static Employee toEntity(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setPhone(dto.getPhone());
        employee.setDob(dto.getDob());
        employee.setGender(dto.getGender() != null ? dto.getGender() : 0);
        employee.setAddress(dto.getAddress());
        employee.setRemark(dto.getRemark());

        if(dto.getDepartment() != null){
            employee.setDepartment(dto.getDepartment());
        }

        return employee;
    }

}
