package com.rb.books.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class BookException extends RuntimeException {
    private int httpStatusCode;
    private String customCode;

    public BookException() {}

    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Throwable e) {
        super(message, e);
    }

    public BookException(int httpStatusCode, String customCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.customCode = customCode;
    }

}
