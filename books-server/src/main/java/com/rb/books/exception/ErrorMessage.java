package com.rb.books.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private int httpStatusCode;
    private String customCode;
    private String message;

    public ErrorMessage(int httpStatusCode, String customCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.customCode = customCode;
        this.message = message;
    }
}
