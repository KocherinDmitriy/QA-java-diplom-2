package api.utils.ingridients;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class IngridientsRequest {

    private static final RequestSpecification REQ_SPEC=
            new RequestSpecBuilder()
                    .setBaseUri("https://stellarburgers.nomoreparties.site")
                    .setBasePath("/api/ingredients")
                    .setContentType(ContentType.JSON)
                    .build();


    @Step("Send POST https://stellarburgers.nomoreparties.site/api/ingredients")
    public static Response getIngridientsRequest() {
        Response response = given()
                .spec(REQ_SPEC)
                .and()
                .get();
        ;
        return response;
    }
}
