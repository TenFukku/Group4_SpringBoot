package com.example.usermanagementdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Đánh dấu đây là RESTful Controller [cite: 289, 402]
@RequestMapping("/api/users") // Đường dẫn gốc cho các API trong class này [cite: 290]
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping // Xử lý HTTP POST để tạo mới [cite: 303]
    public User createUser(@RequestBody User user) { // Lấy user từ body của request [cite: 292, 433]
        return userService.createUser(user);
    }

    @GetMapping // Xử lý HTTP GET để lấy danh sách [cite: 302]
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}