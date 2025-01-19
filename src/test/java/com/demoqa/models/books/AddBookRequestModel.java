package com.demoqa.models.books;

import lombok.Data;

import java.util.List;

@Data
public class AddBookRequestModel {

    private String userId;
    private List<IsbnElement> isbns;

    public AddBookRequestModel(String userId, List<IsbnElement> isbns) {
        this.isbns = isbns;
        this.userId = userId;
    }
}
