package com.demoqa.api;

import com.demoqa.models.login.LoginRequestModel;
import com.demoqa.models.login.UserLogin;
import io.qameta.allure.Step;

import static com.demoqa.specs.GeneralSpecs.requestSpec;
import static com.demoqa.specs.GeneralSpecs.response200Spec;
import static io.restassured.RestAssured.given;

public class AccountApi {

    @Step("Авторизация (через API)")
    public static UserLogin login(String login, String password) {
        LoginRequestModel loginRequest = new LoginRequestModel(login, password);

        return given(requestSpec)
                .body(loginRequest)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(response200Spec)
                .extract().as(UserLogin.class);
    }
}
