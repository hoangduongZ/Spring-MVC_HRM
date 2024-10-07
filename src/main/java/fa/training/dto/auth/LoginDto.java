package fa.training.dto.auth;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class LoginDto {
    @NotBlank(message = "Account is required")
    @Length(min = 4, max = 255, message = "Account must be between 8 and 255 characters")
    private String account;

    @NotBlank(message = "Password is required")
    @Length(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password;
}
