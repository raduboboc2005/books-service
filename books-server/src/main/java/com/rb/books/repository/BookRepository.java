package com.rb.books.repository;

import com.rb.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Book findByIsbn(String bookISBN);
    List<Book> findAll();
    void deleteBookByIsbn(String bookISBN);
}
