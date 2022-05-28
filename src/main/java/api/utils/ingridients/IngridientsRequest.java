package api.utils.ingridients;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class IngridientsRequest {
    @Step("Send POST https://stellarburgers.nomoreparties.site/api/ingredients")
    public static Response getIngridientsRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .get("https://stellarburgers.nomoreparties.site/api/ingredients");
        ;
        return response;
    }
}
