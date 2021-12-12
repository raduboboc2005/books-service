package com.rb.books.service;

import com.rb.books.entity.Book;
import com.rb.books.exception.BookException;
import com.rb.books.repository.BookRepository;
import com.rb.books.server.api.model.BookDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.rb.books.BookUtils.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void testShouldGetBooksSuccess() {
        //GIVEN
        Book book = getBookMock(BOOK_ID, ISBN, TITLE, AUTHOR, STOCK_COPIES);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        when(bookRepository.findAll()).thenReturn(bookList);

        //WHEN
        List<BookDTO> actual = bookService.getBooks();

        //THEN
        BookDTO expected = getBookDTOfromEntity(book);
        Assert.assertEquals(expected, actual.get(0));
    }

    @Test(expected = BookException.class)
    public void testShouldGetBookDetailsException() {
        //GIVEN
        doReturn(null).when(bookRepository).findByIsbn(ISBN);

        //WHEN
        bookService.getBookDetails(ISBN);

        //THEN
        verify(bookRepository, times(1)).findByIsbn(ISBN);
    }
}
