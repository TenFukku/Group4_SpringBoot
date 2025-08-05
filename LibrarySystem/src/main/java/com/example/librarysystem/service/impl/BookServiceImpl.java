package com.example.librarysystem.service.impl;

import com.example.librarysystem.dto.BookDto;
import com.example.librarysystem.entity.Book;
import com.example.librarysystem.repository.BookRepository;
import com.example.librarysystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @PreAuthorize("hasRole('LIBRARIAN')")
    public Book saveFromDto(BookDto bookDto) {
        Book book;
        if (bookDto.getId() != null) {
            book = bookRepository.findById(bookDto.getId()).orElse(new Book());
        } else {
            book = new Book();
        }
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setQuantity(bookDto.getQuantity());
        return bookRepository.save(book);
    }
}