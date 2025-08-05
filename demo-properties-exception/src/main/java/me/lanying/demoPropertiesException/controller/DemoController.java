package me.lanying.demoPropertiesException.controller;

import me.lanying.demoPropertiesException.dto.CreateUserRequest;
import me.lanying.demoPropertiesException.exception.ResoureNotFoundException;
import me.lanying.demoPropertiesException.service.AppInfoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final AppInfoService appInfoService;
    public DemoController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    // === DEMO PROPERTIES ===
    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> getAppInfo() {
        return ResponseEntity.ok(appInfoService.getAppDetails());
    }

    // === DEMO EXCEPTION HANDLING ===

    // Demo ResourceNotFoundException
    @GetMapping("/users/{id}")
    public ResponseEntity<String> getUseById(@PathVariable long id) {
        if (id == 0) {
            // Ném ra ngoại lệ tuỳ chỉnh, sẽ được GlobalExceptionHandler bắt lại
            throw new ResoureNotFoundException("User with ID " + id + " not found.");
        }
        return ResponseEntity.ok("User data for ID: " + id);
    }

    // Demo MethodArgumentNotValidException (validation)
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        // Nếu validation thất bại thì GlobalExceptionHandler sẽ xử lý
        // Nếu thành công thì trả về thông báo thành công
        return ResponseEntity.ok("User " + userRequest.getUsername() + "' created sucessfully.");
    }

    // Demo Exception (Fallback handler)
    @GetMapping("/error")
    public void throwGenericError() {
        throw new NullPointerException("This is a simulated generic error.");
    }
}
