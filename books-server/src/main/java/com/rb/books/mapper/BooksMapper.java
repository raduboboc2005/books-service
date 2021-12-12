package com.rb.books.mapper;

import com.rb.books.entity.Book;
import com.rb.books.server.api.model.BookDTO;

public class BooksMapper {

    public static Book getBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setStockCopies(bookDTO.getStockCopies());

        return book;
    }

    public static BookDTO getBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setStockCopies(book.getStockCopies());

        return bookDTO;
    }
}
