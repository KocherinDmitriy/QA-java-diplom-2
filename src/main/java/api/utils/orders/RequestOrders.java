package api.utils.orders;

import api.utils.BaseSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;


public class RequestOrders extends BaseSpec {
    public RequestOrders() {
    }

    @Step("Send GET https://stellarburgers.nomoreparties.site/api/orders/all")
    public  Response allOrders(String authToken) {
        Response response = given()
                .spec(REQ_SPEC).auth().oauth2(authToken)
                .and()
                .get("/api/orders/all");
        ;
        return response;
    }

    @Step("Send GET https://stellarburgers.nomoreparties.site/api/orders")
    public  Response allMyOrders(String authToken) {
        Response response = given()
                .spec(REQ_SPEC).auth().oauth2(authToken)
                .and()
                .get("/api/orders");
        ;
        return response;
    }
}
