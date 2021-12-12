package com.rb.books.controller;

import com.rb.books.server.api.BooksApi;
import com.rb.books.server.api.model.BookDTO;
import com.rb.books.server.api.model.BookDTOList;
import com.rb.books.server.api.model.StockCopies;
import com.rb.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/books-service/v1")
public class BooksController implements BooksApi {

    @Autowired
    private BookService bookService;

    @Override
    public ResponseEntity<Void> updateBookStockCopies(@PathVariable("isbn") String isbn,
                                                      @Valid @RequestBody(required = false) StockCopies stockCopies) {
        bookService.updateBookStockCopies(isbn, stockCopies);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BookDTO> updateBookDetails(@Valid @RequestBody(required = false) BookDTO bookDTO) {
        bookService.updateBookDetails(bookDTO);
        return ResponseEntity.ok(bookDTO);
    }

    @Override
    public ResponseEntity<BookDTO> getBookDetails(@PathVariable("isbn") String isbn) {
        return ResponseEntity.ok(bookService.getBookDetails(isbn));
    }

    @Override
    public ResponseEntity<Void> deleteBook(@PathVariable("isbn") String isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<BookDTOList> getBooks() {
        List<BookDTO> bookDTOs = bookService.getBooks();
        BookDTOList bookDTOList = new BookDTOList();
        bookDTOList.setBooks(bookDTOs);
        return ResponseEntity.ok(bookDTOList);
    }

    @Override
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO book) {
        bookService.addBook(book);
        return ResponseEntity.ok(book);
    }
}
