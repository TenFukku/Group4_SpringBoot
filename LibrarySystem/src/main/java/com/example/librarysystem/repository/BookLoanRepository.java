package com.example.librarysystem.repository;

import com.example.librarysystem.entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    List<BookLoan> findByUserUsernameOrderByLoanDateDesc(String username);
}