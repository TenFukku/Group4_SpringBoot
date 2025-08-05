package com.example.usermanagementdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Đánh dấu đây là một Repository bean [cite: 375, 99]
public interface UserRepository extends JpaRepository<User, Long> {
}