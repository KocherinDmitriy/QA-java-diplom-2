package api.utils.createuser;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUser {

    @Step("Send POST https://stellarburgers.nomoreparties.site/api/auth/register")
    public static Response sendPostRequestCreateUser(String email, String password, String name) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"email\":\"" + email + "\","
                        + "\"password\":\"" + password + "\","
                        + "\"name\":\"" + name + "\"}")
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/register");
        ;
        return response;
    }
    @Step("Compare answer to server")
    public static void compareResponse(Response response, String field, String message) {
        response.then().assertThat().body(field, equalTo(message));
    }

    @Step("Compare answer to server")
    public static void compareResponseWithBollean(Response response, String field, Boolean message) {
        response.then().assertThat().body(field, equalTo(message));
    }
}
