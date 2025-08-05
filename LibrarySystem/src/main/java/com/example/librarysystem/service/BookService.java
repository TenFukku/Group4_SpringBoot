package com.example.librarysystem.service;

import com.example.librarysystem.dto.BookDto;
import com.example.librarysystem.entity.Book;
import java.util.List;

public interface BookService {
    List<Book> findAll();
    void deleteById(Long id);
    Book findById(Long id);
    Book saveFromDto(BookDto bookDto);
}