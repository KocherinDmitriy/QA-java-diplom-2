package api.utils.personaldata;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ChangePersonalData {
    @Step("Send POST https://stellarburgers.nomoreparties.site/api/auth/register")
    public static Response sendPatchRequestChangeUser(String authToken, String email, String password, String name) {
        Response response = given()
                .header("Content-type", "application/json").auth().oauth2(authToken)
                .and()
                .body("{\"email\":\"" + email + "\","
                        + "\"password\":\"" + password + "\","
                        + "\"name\":\"" + name + "\"}")
                .when()
                .patch("https://stellarburgers.nomoreparties.site/api/auth/user");
        ;
        return response;
    }
}