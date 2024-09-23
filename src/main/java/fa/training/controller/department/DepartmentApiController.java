package fa.training.controller.department;

import fa.training.dto.department.DepartmentDto;
import fa.training.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentApiController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments(){
        List<DepartmentDto> departmentDtos = departmentService.findAll();
        return ResponseEntity.ok(departmentDtos);
    }
}
