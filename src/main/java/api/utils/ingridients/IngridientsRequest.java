package api.utils.ingridients;

import api.utils.BaseSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class IngridientsRequest extends BaseSpec {
    public IngridientsRequest() {
    }

    @Step("Send POST https://stellarburgers.nomoreparties.site/api/ingredients")
    public  Response getIngridientsRequest() {
        Response response = given()
                .spec(REQ_SPEC)
                .and()
                .get("/api/ingredients");
        ;
        return response;
    }
}
