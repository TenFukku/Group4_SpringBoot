package me.lanying.demoPropertiesException.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
// Chỉ bao gồm các trường không null trong JSON output
public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> validationErrors;
}
