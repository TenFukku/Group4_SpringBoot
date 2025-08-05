package com.example.librarysystem.controller;

import com.example.librarysystem.dto.BookDto;
import com.example.librarysystem.entity.Book;
import com.example.librarysystem.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/manage/books")
@RequiredArgsConstructor
@PreAuthorize("hasRole('LIBRARIAN')") // Chỉ Librarian mới được truy cập controller này
public class ManageBookController {

    private final BookService bookService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "manage-books";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("pageTitle", "Thêm sách mới");
        return "book-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/manage/books";
        }
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setQuantity(book.getQuantity());

        model.addAttribute("bookDto", bookDto);
        model.addAttribute("pageTitle", "Chỉnh sửa sách");
        return "book-form";
    }

    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("bookDto") BookDto bookDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", bookDto.getId() == null ? "Thêm sách mới" : "Chỉnh sửa sách");
            return "book-form";
        }

        bookService.saveFromDto(bookDto);
        redirectAttributes.addFlashAttribute("successMessage", "Lưu sách thành công!");
        return "redirect:/manage/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa sách này.");
        }
        return "redirect:/manage/books";
    }
}