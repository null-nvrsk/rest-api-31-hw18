package com.demoqa.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.demoqa.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class GeneralSpecs {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().method()
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(JSON);

    public static ResponseSpecification response200Spec = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification response201Spec = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification response204Spec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .expectStatusCode(204)
            .build();
}
