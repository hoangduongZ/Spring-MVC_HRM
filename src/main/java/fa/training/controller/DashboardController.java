package fa.training.controller;

import fa.training.service.DepartmentService;
import fa.training.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("dashboard")
public class DashboardController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @GetMapping
    public String showDashboard(Model model) {

        long employeeCount = employeeService.countAllEmployees();
        long departmentCount = departmentService.countAllDepartments();

        model.addAttribute("employeeCount", employeeCount);
        model.addAttribute("departmentCount", departmentCount);

        return "home/dashboard";
    }
}
