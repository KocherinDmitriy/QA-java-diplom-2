package api.utils.orders;

import api.utils.BaseSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;


public class RequestOrders extends BaseSpec {


    @Step("Send GET https://stellarburgers.nomoreparties.site/api/orders/all")
    public static Response allOrders(String authToken) {
        Response response = given()
                .spec(REQ_SPEC).auth().oauth2(authToken)
                .and()
                .get("/api/orders/all");
        ;
        return response;
    }

    @Step("Send GET https://stellarburgers.nomoreparties.site/api/orders")
    public static Response allMyOrders(String authToken) {
        Response response = given()
                .spec(REQ_SPEC).auth().oauth2(authToken)
                .and()
                .get("/api/orders");
        ;
        return response;
    }
}
