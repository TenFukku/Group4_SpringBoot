package com.example.librarysystem.controller;

import com.example.librarysystem.service.BookLoanService;
import com.example.librarysystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookLoanService bookLoanService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "index";
    }

    @GetMapping("/books/borrow/{id}")
    public String borrowBook(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {
        if (principal == null) {
            return "redirect:/login";
        }
        try {
            bookLoanService.borrowBook(id, principal.getName());
            redirectAttributes.addFlashAttribute("successMessage", "Mượn sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mượn sách thất bại: " + e.getMessage());
        }
        return "redirect:/";
    }
}