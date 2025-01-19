package com.demoqa.tests;

import com.demoqa.api.AccountApi;
import com.demoqa.api.BookStoreApi;
import com.demoqa.models.books.AddBookRequestModel;
import com.demoqa.models.books.IsbnElement;
import com.demoqa.models.login.LoginRequestModel;
import com.demoqa.models.login.LoginResponseModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BookCollectionTests extends TestBase {

    @Test
    void deleteBookFromCollectionTest() {
        LoginResponseModel user = AccountApi.login(
                new LoginRequestModel(UserData.login, UserData.password)
        );

        BookStoreApi.deleteAllBooks(user.getUserId(), user.getToken());

        String isbn = "9781449337711";
        /// ??? BUG
        Response response = BookStoreApi.addBook(
                new AddBookRequestModel(
                        user.getUserId(),
                        List.of(new IsbnElement(isbn))
                ),
                user.getToken()
        );

        // TODO UI delete
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", user.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", user.getExpires().toString()));
        getWebDriver().manage().addCookie(new Cookie("token", user.getToken()));

        open("/profile");

        String bookTitle = "Designing Evolvable Web APIs with ASP.NET";
        $(".ReactTable").shouldHave(text(bookTitle));

        $(".ReactTable #delete-record-undefined").click();
        $(".ReactTable").shouldNotHave(text(bookTitle));
    }

//    @Test
//    void addBookToCollectionTest() {
//
//        LoginResponseModel user = AccountApi.login(
//                new LoginRequestModel(UserData.login, UserData.password)
//        );
//
//        BookStoreApi.deleteAllBooks(user.getUserId(), user.getToken());
//
//        String isbn = "9781449337711";
//        String bookData = "{\"userId\": \"" + authResponse.path("userId") + "\", \"collectionOfIsbns\": [ "
//                + "{\"isbn\": \"" + isbn + "\" }] }";
//
//        Response addBookResponse = given()
//                .log().uri()
//                .log().method()
//                .log().body()
//                .contentType(JSON)
//                .header("Authorization", "Bearer " + UserData.token)
//                .body(bookData)
//                .when()
//                .post("/BookStore/v1/Books")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(201)
//                .extract().response();
//
//        open("/favicon.ico");
//        getWebDriver().manage().addCookie(new Cookie("userID", user.getUserId()));
//        getWebDriver().manage().addCookie(new Cookie("expires", user.getExpires().toString()));
//        getWebDriver().manage().addCookie(new Cookie("token", user.getToken()));
//
//        open("/profile");
//
//        String bookTitle = "Designing Evolvable Web APIs with ASP.NET";
//        $(".ReactTable").shouldHave(text(bookTitle));
//    }
//
//    @Test
//    void addBookToCollectionWithDelete1BookTest() {
//        LoginResponseModel loginResponseModel = AccountApi.login(
//                new LoginRequestModel(UserData.login, UserData.password)
//        );
//
//        String deleteIsbn = "9781449337711";
//        String deleteBookData = "{\"isbn\": \"" + deleteIsbn + "\",\"userId\": \"" + authResponse.path("userId") + "\" }";
//
//        given()
//                .log().uri()
//                .log().method()
//                .log().body()
//                .contentType(JSON)
//                .header("Authorization", "Bearer " + UserData.token)
//                .body(deleteBookData)
//            .when()
//                .delete("/BookStore/v1/Book")
//            .then()
//                .log().status()
//                .log().body()
//                .statusCode(204);
//
//        String isbn = "9781449337711";
//        String bookData = "{\"userId\": \"" + authResponse.path("userId") + "\", \"collectionOfIsbns\": [ "
//                + "{\"isbn\": \"" + isbn + "\" }] }";
//
//        Response addBookResponse = given()
//                .log().uri()
//                .log().method()
//                .log().body()
//                .contentType(JSON)
//                .header("Authorization", "Bearer " + authResponse.path("token"))
//                .body(bookData)
//                .when()
//                .post("/BookStore/v1/Books")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(201)
//                .extract().response();
//
//        open("/favicon.ico");
//        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
//        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
//        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));
//
//        open("/profile");
//
//        String bookTitle = "Designing Evolvable Web APIs with ASP.NET";
//        $(".ReactTable").shouldHave(text(bookTitle));
//    }
//
//
//    @Test
//    void negative400addExistBookToCollectionTest() {
//        LoginResponseModel loginResponseModel = AccountApi.login(
//                new LoginRequestModel(UserData.login, UserData.password)
//        );
//
//        String isbn = "9781449337711";
//        String bookData = "{\"userId\": \"" + authResponse.path("userId") + "\", \"collectionOfIsbns\": [ "
//                + "{\"isbn\": \"" + isbn + "\" }] }";
//
//        given()
//                .log().uri()
//                .log().method()
//                .log().body()
//                .contentType(JSON)
//                .header("Authorization", "Bearer " + authResponse.path("token"))
//                .body(bookData)
//                .when()
//                .post("/BookStore/v1/Books")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(400)
//                .body("code", is("1210"))
//                .body("message", is("ISBN already present in the User's Collection!"));
//    }
//
//    @Test
//    void negative401addBookToCollectionTest() {
//
//        String userId = "2700be58-3d40-4a67-a148-631e781f936e";
//        String isbn = "9781449337711";
//        String bookData = "{\"userId\": \"" + userId + "\", \"collectionOfIsbns\": [{\"isbn\": \"" + isbn + "\" }]}";
//
//        given()
//                .log().uri()
//                .log().method()
//                .log().body()
//                .contentType(JSON)
//                .body(bookData)
//                .when()
//                .post("/BookStore/v1/Books")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(401)
//                .body("code", is("1200"))
//                .body("message", is("User not authorized!"));
//    }
}
