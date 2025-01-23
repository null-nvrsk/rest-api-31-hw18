package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.api.BookStoreApi;
import com.demoqa.config.AuthConfig;
import com.demoqa.helpers.Attach;
import com.demoqa.pages.ProfilePage;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    BookStoreApi bookStoreApi = new BookStoreApi();
    ProfilePage profilePage = new ProfilePage();
    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "125");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        if (System.getProperty("selenoidUrl") != null) {
            Configuration.remote = "https://" +
                    System.getProperty("selenoidAuth") + "@"
                    + System.getProperty("selenoidUrl") + "/wd/hub";

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachment() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();
    }
}
