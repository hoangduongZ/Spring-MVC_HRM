package fa.training.controller.employee;

import fa.training.dto.employee.EmployeeDto;
import fa.training.service.AccountService;
import fa.training.service.DepartmentService;
import fa.training.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("employees")
public class EmployeeController {
    private EmployeeService employeeService;
    private AccountService accountService;
    private DepartmentService departmentService;

    @GetMapping("add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new EmployeeDto());
        model.addAttribute("departments", departmentService.findAll());
        return "employee/add-employee";
    }


    @PostMapping("add")
    public String postAddEmployee(@Valid @ModelAttribute("employee") EmployeeDto employeeDto,
                                  Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employeeDto);
            return "employee/add-employee";
        }
        try {
            employeeService.save(employeeDto);
            return "redirect:/employees?success=true";
        } catch (DataIntegrityViolationException e) {
            String message = e.getMostSpecificCause().getMessage();
            if (message.contains("duplicate key value")) {
                if (isAccountExists(employeeDto.getAccountDto().getAccount())) {
                    model.addAttribute("accountError", "Account name is existed !");
                }
                if (isEmailExists(employeeDto.getAccountDto().getEmail())) {
                    model.addAttribute("emailError", "Email is existed !");
                }
            }
            model.addAttribute("departments", departmentService.findAll());
            return "employee/add-employee";
        }
    }

    private boolean isAccountExists(String account) {
        return accountService.existsByAccount(account);
    }

    private boolean isEmailExists(String email) {
        return accountService.existsByEmail(email);
    }

    @GetMapping()
    public String getEmployees(@RequestParam(required = false) String keyword,
                               @RequestParam(defaultValue = "firstName") String sort,
                               @RequestParam(defaultValue = "asc") String order,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                             Model model) {
        Sort.Direction direction = Sort.Direction.fromString(order);

        Sort sortOrder;
        sortOrder = Sort.by(direction, sort);

        var pageable = PageRequest.of(page, size, sortOrder);
        var employees = employeeService.pagingAndSorting(keyword, pageable);

        model.addAttribute("employees", employees);
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("sort", sort == null ? "firstName" : sort);
        model.addAttribute("order", order.equals("asc") ? "asc" : "desc");
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", employees.getTotalPages());
        model.addAttribute("totalElements", employees.getTotalElements());
        model.addAttribute("pageSizes", new int[]{2, 5, 10, 20});
        return "employee/list-employee";
    }


    @GetMapping("/delete/{id}")
    public String getEmployeeDelete(@PathVariable UUID id) {
        EmployeeDto employeeDto = employeeService.getById(id);

        if (employeeDto == null) {
            throw new IllegalStateException("No existed Employees");
        }

        employeeService.deleteEmp(id);
        return "redirect:/employees";
    }
}
