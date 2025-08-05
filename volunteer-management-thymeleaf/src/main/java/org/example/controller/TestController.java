package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("message", "Hello from Test Controller!");
        return "test";
    }
    
    @GetMapping("/simple")
    public String simple() {
        return "home";
    }
} 