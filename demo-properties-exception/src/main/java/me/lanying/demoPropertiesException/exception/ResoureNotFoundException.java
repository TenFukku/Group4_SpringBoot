package me.lanying.demoPropertiesException.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Khi ngoại lệ này được ném ra và không được bắt, nó mặc định trả về 404
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResoureNotFoundException extends RuntimeException{
    public ResoureNotFoundException(String message) {
        super(message);
    }
}
