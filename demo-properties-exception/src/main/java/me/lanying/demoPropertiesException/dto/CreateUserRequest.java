package me.lanying.demoPropertiesException.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Data
public class CreateUserRequest {
    @NotEmpty(message = "Username cannot be empty.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    private String username;

    @NotEmpty(message = "Email cannot be empty.")
    @Email(message = "Email should be valid.")
    private String email;
}
