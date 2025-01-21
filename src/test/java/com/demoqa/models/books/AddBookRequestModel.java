package com.demoqa.models.books;

import lombok.Data;

import java.util.List;

@Data
public class AddBookRequestModel {

    private String userId;
    private List<IsbnElement> collectionOfIsbns;

    public AddBookRequestModel(String userId, List<IsbnElement> isbns) {
        this.collectionOfIsbns = isbns;
        this.userId = userId;
    }
}
