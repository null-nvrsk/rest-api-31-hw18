package com.demoqa.tests;

import com.demoqa.api.AccountApi;
import com.demoqa.api.BookStoreApi;
import com.demoqa.models.login.LoginRequestModel;
import com.demoqa.models.login.UserLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BookCollectionTests extends TestBase {

    String bookIsbn = "9781449337711";
    String bookTitle = "Designing Evolvable Web APIs with ASP.NET";

    @Test
    @DisplayName("Удаление книги из коллекции")
    void deleteBookFromCollectionTest() {
        UserLogin user = AccountApi.login(
                new LoginRequestModel(UserData.login, UserData.password)
        );
        String userId = user.getUserId();
        String token = user.getToken();

        BookStoreApi.deleteAllBooks(userId, token);
        BookStoreApi.addBook(bookIsbn, userId, token);

        profilePage.openPage(user);
        profilePage.deleteBook(bookTitle);
        profilePage.verifyDeletion(bookTitle);
    }
}
