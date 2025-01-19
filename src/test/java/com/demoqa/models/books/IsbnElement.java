package com.demoqa.models.books;

import lombok.Data;

@Data
public class IsbnElement {

    private String isbn;

    public IsbnElement(String isbn) {
        this.isbn = isbn;
    }
}
