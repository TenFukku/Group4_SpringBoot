package com.example.librarysystem.service.impl;

import com.example.librarysystem.entity.Book;
import com.example.librarysystem.entity.BookLoan;
import com.example.librarysystem.entity.User;
import com.example.librarysystem.exception.BookNotAvailableException;
import com.example.librarysystem.repository.BookLoanRepository;
import com.example.librarysystem.repository.BookRepository;
import com.example.librarysystem.repository.UserRepository;
import com.example.librarysystem.service.BookLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookLoanRepository bookLoanRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void borrowBook(Long bookId, String username) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sách với ID: " + bookId));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng: " + username));

        if (book.getQuantity() <= 0) {
            throw new BookNotAvailableException("Sách \"" + book.getTitle() + "\" đã hết.");
        }

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        BookLoan loan = new BookLoan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(LocalDate.now());
        loan.setStatus("BORROWED");
        bookLoanRepository.save(loan);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnBook(Long loanId, String username) {
        BookLoan loan = bookLoanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lượt mượn sách."));

        // Chỉ chủ nhân của lượt mượn mới được trả sách
        if (!loan.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("Bạn không có quyền trả sách này.");
        }

        if (loan.getStatus().equals("RETURNED")) {
            throw new IllegalStateException("Sách này đã được trả rồi.");
        }

        loan.setStatus("RETURNED");
        loan.setReturnDate(LocalDate.now());
        bookLoanRepository.save(loan);

        Book book = loan.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }

    @Override
    public List<BookLoan> findByUsername(String username) {
        return bookLoanRepository.findByUserUsernameOrderByLoanDateDesc(username);
    }
}