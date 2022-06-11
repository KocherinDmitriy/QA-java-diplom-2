package api.utils.orders;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class RequestOrders {

    private static final RequestSpecification REQ_SPEC=
            new RequestSpecBuilder()
                    .setBaseUri("https://stellarburgers.nomoreparties.site")
                    .setBasePath("/api/orders")
                    .setContentType(ContentType.JSON)
                    .build();


    @Step("Send GET https://stellarburgers.nomoreparties.site/api/orders/all")
    public static  Response allOrders(String authToken) {
        Response response = given()
                .spec(REQ_SPEC).auth().oauth2(authToken)
                .and()
                .get(" /all");
        ;
        return response;
    }
        @Step("Send GET https://stellarburgers.nomoreparties.site/api/orders")
        public static  Response allMyOrders(String authToken) {
            Response response = given()
                    .spec(REQ_SPEC).auth().oauth2(authToken)
                    .and()
                    .get();
            ;
            return response;
        }
}
