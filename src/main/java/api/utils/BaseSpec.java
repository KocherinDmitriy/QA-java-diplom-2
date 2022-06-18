package api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseSpec {

    public static  final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://stellarburgers.nomoreparties.site")
                    .setContentType(ContentType.JSON)
                    .build();
}
