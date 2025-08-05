package com.example.usermanagementdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Đánh dấu đây là một Service bean [cite: 374, 97]
public class UserService {
    @Autowired // Tự động tiêm UserRepository [cite: 377]
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}