package com.demoqa.api;

import com.demoqa.models.books.AddBookRequestModel;
import com.demoqa.models.books.IsbnElement;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static com.demoqa.specs.GeneralSpecs.*;
import static io.restassured.RestAssured.given;

public class BookStoreApi {

    @Step("Удаление всех книг (через API)")
    public void deleteAllBooks(String userId, String token) {
        given(requestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParams("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(response204Spec);
    }

    @Step("Добавление книги (через API)")
    public Response addBook(String isbn, String userId, String token) {
        AddBookRequestModel requestBody = new AddBookRequestModel(
                userId,
                List.of(new IsbnElement(isbn))
        );
        return given(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(response201Spec)
                .extract().response();
    }
}
