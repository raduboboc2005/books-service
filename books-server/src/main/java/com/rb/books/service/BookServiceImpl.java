package com.rb.books.service;

import com.rb.books.entity.Book;
import com.rb.books.exception.BookException;
import com.rb.books.mapper.BooksMapper;
import com.rb.books.repository.BookRepository;
import com.rb.books.server.api.model.BookDTO;
import com.rb.books.server.api.model.StockCopies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.rb.books.constants.BookConstants.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDTO> getBooks() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> books = bookRepository.findAll();

        if (books != null) {
           bookDTOS = books.stream().map(BooksMapper::getBookDTO).collect(Collectors.toList());
        }

        return bookDTOS;
    }

    @Override
    @Transactional
    public void deleteBook(String bookISBN) {
        try {
            bookRepository.deleteBookByIsbn(bookISBN);
        } catch (Exception e) {
            throw new BookException(String.format(BOOK_DELETION_FAILURE, bookISBN));
        }
    }

    @Override
    public BookDTO getBookDetails(String bookISBN) {
        try {
            Book book = bookRepository.findByIsbn(bookISBN);

            if (book == null) {
                throw new BookException(HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(), String.format(BOOK_NOT_FOUND, bookISBN));
            }
            return BooksMapper.getBookDTO(book);
        } catch (Exception e) {
            throw new BookException(String.format(BOOK_NOT_FOUND, bookISBN));
        }
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = BooksMapper.getBook(bookDTO);
        try {
            bookRepository.save(book);
        } catch (Exception e) {
            throw new BookException(String.format(BOOK_SAVE_FAILURE, bookDTO.getIsbn()));
        }
        return bookDTO;
    }

    @Override
    public BookDTO updateBookDetails(BookDTO bookDTO) {
        Book book = BooksMapper.getBook(bookDTO);
        try {
            bookRepository.saveAndFlush(book);
        } catch (Exception e) {
            throw new BookException(String.format(UPDATE_BOOK_FAILURE, bookDTO.getIsbn()));
        }

        return bookDTO;
    }

    @Override
    public void updateBookStockCopies(String isbn, StockCopies stockCopies) {
       Book book = bookRepository.findByIsbn(isbn);
       if(book == null) {
           throw new BookException(String.format(BOOK_NOT_FOUND, isbn));
       }

       book.setStockCopies(stockCopies.getStockCopies());

       try {
           bookRepository.saveAndFlush(book);
       } catch (Exception e) {
           throw new BookException(String.format(UPDATE_BOOK_FAILURE, isbn));
       }

    }
}
