package com.rb.books.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BOOK")
@Getter
@Setter
public class Book {

    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;

    @Id
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "STOCK_COPIES", nullable = false)
    private Integer stockCopies;
}
