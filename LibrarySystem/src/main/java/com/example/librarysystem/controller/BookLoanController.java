package com.example.librarysystem.controller;

import com.example.librarysystem.service.BookLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()") // Yêu cầu đăng nhập cho tất cả các chức năng
public class BookLoanController {

    private final BookLoanService bookLoanService;

    @GetMapping("/my-loans")
    public String myLoans(Model model, Principal principal) {
        model.addAttribute("loans", bookLoanService.findByUsername(principal.getName()));
        return "my-loans";
    }

    @GetMapping("/loans/return/{id}")
    public String returnBook(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {
        try {
            bookLoanService.returnBook(id, principal.getName());
            redirectAttributes.addFlashAttribute("successMessage", "Trả sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Trả sách thất bại: " + e.getMessage());
        }
        return "redirect:/my-loans";
    }
}