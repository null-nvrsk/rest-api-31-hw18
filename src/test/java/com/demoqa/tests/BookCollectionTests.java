package com.demoqa.tests;

import com.demoqa.api.AccountApi;
import com.demoqa.models.login.UserLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BookCollectionTests extends BaseTest {

    String bookIsbn = "9781449337711";
    String bookTitle = "Designing Evolvable Web APIs with ASP.NET";

    @Test
    @DisplayName("Удаление книги из коллекции")
    void deleteBookFromCollectionTest() {
        UserLogin user = AccountApi.login(config.login(), config.password());
        String userId = user.getUserId();
        String token = user.getToken();
        String username = user.getUsername();

        bookStoreApi.deleteAllBooks(userId, token);
        bookStoreApi.addBook(bookIsbn, userId, token);

        profilePage.addAuthorizedUserCookies(user)
                .openPage()
                .verifyUserProfile(username)
                .verifyBookInCollection(bookTitle)
                .deleteBook(bookTitle)
                .verifyDeletion(bookTitle);
    }
}
