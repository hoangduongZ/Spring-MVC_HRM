package fa.training.controller.employee;

import fa.training.dto.employee.EmployeeDto;
import fa.training.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RestController
@RequestMapping("api/employees")
public class EmployeeApiController {
    private final EmployeeService employeeService;

    public EmployeeApiController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable UUID id) {
        EmployeeDto employeeDto = employeeService.getById(id);
        if (employeeDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(employeeDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable UUID id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee = employeeService.updateById(id, employeeDto);
        if (updatedEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }



}