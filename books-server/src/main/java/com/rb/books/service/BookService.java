package com.rb.books.service;

import com.rb.books.server.api.model.BookDTO;
import com.rb.books.server.api.model.StockCopies;

import java.util.List;

public interface BookService {
    List<BookDTO> getBooks();
    void deleteBook(String bookISBN);
    BookDTO getBookDetails(String bookISBN);
    BookDTO addBook(BookDTO bookDTO);
    BookDTO updateBookDetails(BookDTO bookDTO);
    void updateBookStockCopies(String isbn, StockCopies stockCopies);
}
