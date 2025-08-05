package me.lanying.demoPropertiesException.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Xử lý ResourceNotFoundException
    @ExceptionHandler(ResoureNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResoureNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError("Not Found");
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath(request.getDescription(false).replace("uri= ", ""));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Xử lý lỗi validation từ @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError("Bad Request");
        errorResponse.setMessage("Validation failed for request body.");
        errorResponse.setPath(request.getDescription(false).replace("uri= ", ""));
        errorResponse.setValidationErrors(errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Xử lý tất cả các ngoại lệ khác (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerGlobalException(Exception ex, WebRequest request) {
        // Ghi log lỗi ở đây cho mục đich gỡ lỗi
        // log.error("An unexpected error occured: ", ex);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setError("Internal Server Error");
        errorResponse.setMessage("An unexpected internal server error occured.");
        errorResponse.setPath(request.getDescription(false).replace("uri= ", ""));

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
