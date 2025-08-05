package com.example.librarysystem.service;

import com.example.librarysystem.entity.BookLoan;
import java.util.List;

public interface BookLoanService {
    void borrowBook(Long bookId, String username);
    void returnBook(Long loanId, String username);
    List<BookLoan> findByUsername(String username);
}