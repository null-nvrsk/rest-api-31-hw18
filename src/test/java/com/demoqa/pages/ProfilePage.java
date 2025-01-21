package com.demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.demoqa.models.login.UserLogin;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {

    @Step("Открыть список книг пользователя")
    public ProfilePage openPage(UserLogin user) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", user.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", user.getExpires().toString()));
        getWebDriver().manage().addCookie(new Cookie("token", user.getToken()));

        open("/profile");

        return this;
    }

    @Step("Удалить книгу \"{bookTitle}\"")
    public ProfilePage deleteBook(String bookTitle) {
        $(".ReactTable").shouldHave(text(bookTitle));

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
