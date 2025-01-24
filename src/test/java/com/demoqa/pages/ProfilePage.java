package com.demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.demoqa.models.login.UserLogin;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {

    @Step("Добавить куки авторизованного пользователя")
    public ProfilePage addAuthorizedUserCookies(UserLogin user) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", user.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", user.getExpires().toString()));
        getWebDriver().manage().addCookie(new Cookie("token", user.getToken()));

        return this;
    }

    @Step("Открыть список книг пользователя")
    public ProfilePage openPage() {
        open("/profile");

        return this;
    }

    @Step("Проверить, что пользователь \"{username}\" авторизован")
    public ProfilePage verifyUserProfile(String username) {
        $("#userName-value").shouldHave(text(username));

        return this;
    }

    @Step("Проверить, что книга \"{bookTitle}\" в коллекции")
    public ProfilePage verifyBookInCollection(String bookTitle) {
        $(".ReactTable").shouldHave(text(bookTitle));

        return this;
    }

    @Step("Удалить книгу \"{bookTitle}\"")
    public ProfilePage deleteBook(String bookTitle) {
        $(".ReactTable #delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
        Selenide.confirm();

        return this;
    }

    @Step("Проверить, что книги \"{bookTitle}\" нет в списке")
    public void verifyDeletion(String bookTitle) {
        $(".ReactTable").shouldNotHave(text(bookTitle));
    }
}
