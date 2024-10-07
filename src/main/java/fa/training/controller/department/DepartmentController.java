package fa.training.controller.department;

import fa.training.dto.department.DepartmentDto;
import fa.training.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@EnableMethodSecurity
@RequestMapping("departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public String addGetDepartment(Model model){
        model.addAttribute("department", new DepartmentDto());
        model.addAttribute("departments",departmentService.findAll());
        return "department/department";
    }

    @PostMapping
    public String postAddDepartment(@Valid @ModelAttribute("department") DepartmentDto departmentDto){
        if(departmentDto.getId() != null){
            throw new IllegalStateException("Department should be no id! Because Id auto increment !");
        }
        departmentService.addDepartment(departmentDto);
        return "redirect:/departments";
    }



}
