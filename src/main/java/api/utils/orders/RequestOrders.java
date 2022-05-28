package api.utils.orders;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestOrders {
    @Step("Send GET https://stellarburgers.nomoreparties.site/api/orders/all")
    public static  Response allOrders(String authToken) {
        Response response = given()
                .header("Content-type", "application/json").auth().oauth2(authToken)
                .and()
                .get(" https://stellarburgers.nomoreparties.site/api/orders/all");
        ;
        return response;
    }
        @Step("Send GET https://stellarburgers.nomoreparties.site/api/orders")
        public static  Response allMyOrders(String authToken) {
            Response response = given()
                    .header("Content-type", "application/json").auth().oauth2(authToken)
                    .and()
                    .get(" https://stellarburgers.nomoreparties.site/api/orders");
            ;
            return response;
        }
}
