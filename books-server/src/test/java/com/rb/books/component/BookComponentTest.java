package com.rb.books.component;

import com.rb.books.entity.Book;
import com.rb.books.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.ArrayList;
import java.util.List;

import static com.rb.books.BookUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookComponentTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OAuthHelper oAuthHelper;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testShouldGetListOfBooksSuccess() throws Exception {

        Book book = getBookMock(BOOK_ID, ISBN, TITLE, AUTHOR, STOCK_COPIES);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        when(bookRepository.findAll()).thenReturn(bookList);

        RequestPostProcessor bearerToken = oAuthHelper.bearerToken("client_app", "test_user");
        MvcResult mvcResult = mockMvc.perform(
                get("/books-service/v1" + "/books")
                        .with(bearerToken))
                        .andExpect(status().isOk())
                        .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        String expected = "{\"books\":[{\"isbn\":\"987-2-14-12341-9\",\"title\":\"Jane Red\",\"author\":\"WIlliam Henry\",\"stockCopies\":5},{\"isbn\":\"937-2-14-12901-1\",\"title\":\"Disturbance\",\"author\":\"Ben Miles\",\"stockCopies\":7}]}";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testShouldGetListOfBooksNotFoundFailure() throws Exception {

        Book book = getBookMock(BOOK_ID, ISBN, TITLE, AUTHOR, STOCK_COPIES);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        when(bookRepository.findAll()).thenReturn(bookList);

        RequestPostProcessor bearerToken = oAuthHelper.bearerToken("client_app", "test_user");
        MvcResult mvcResult = mockMvc.perform(
                get("/books-service/v1" + "/booooks")
                        .with(bearerToken))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void testAddNewBookSuccess() throws Exception {
        Book book = getBookMock(BOOK_ID, ISBN, TITLE, AUTHOR, STOCK_COPIES);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        RequestPostProcessor bearerToken = oAuthHelper.bearerToken("client_app", "test_user");
        mockMvc.perform(
                post("/books-service/v1" + "/books")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(getBookDTOJson())
                        .with(bearerToken))
                .andExpect(status().isOk())
                .andReturn();

        Mockito.verify(bookRepository, times(1)).save(any(Book.class));
    }
}
