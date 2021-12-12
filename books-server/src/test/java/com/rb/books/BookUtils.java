package com.rb.books;

import com.rb.books.entity.Book;
import com.rb.books.server.api.model.BookDTO;

public class BookUtils {

    public static final String ISBN = "123-1212-441";
    public static final String TITLE = "Mein Buch";
    public static final String AUTHOR = "Gerhard Herl";
    public static final Integer STOCK_COPIES = 10;
    public static final Integer BOOK_ID = 1;


    public static String getBookDTOJson() {
        String bookDTO = "{\"isbn\":\"987-2-14-12341-9\",\"title\":\"Jane Red\",\"author\":\"WIlliam Henry\",\"stockCopies\":5}";
        return bookDTO;
    }

    public static Book getBookMock(Integer bookId, String isbn, String title, String author, Integer stockCopies) {
        Book book = new Book();
        book.setBookId(bookId);
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setStockCopies(stockCopies);

        return book;
    }

    public static BookDTO getBookDTOMock(String isbn, String title, String author, Integer stockCopies) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(title);
        bookDTO.setAuthor(author);
        bookDTO.setIsbn(isbn);
        bookDTO.setStockCopies(stockCopies);

        return bookDTO;
    }


    public static BookDTO getBookDTOfromEntity(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setStockCopies(book.getStockCopies());

        return bookDTO;
    }
}
