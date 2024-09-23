package fa.training.dto.employee;

import fa.training.dto.account.AccountDto;
import fa.training.entity.Department;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private UUID id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Phone number is mandatory")
    private String phone;

    private LocalDate dob;

    @Min(0)
    @Max(1)
    private Integer gender;

    private Department department;

    private String remark;

    private String address;

    private AccountDto accountDto;
}