package api.utils.auth;

import api.utils.BaseSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Auth extends BaseSpec {

    public static HashMap getBodyAuthUserRequest(String email, String password) {
        HashMap<String, Object> dataBody = new HashMap<String, Object>();
        dataBody.put("email", email);
        dataBody.put("password", password);


        return dataBody;
    }


    public static String getAuthToken(String email, String password) {
        Response response = given()
                .spec(REQ_SPEC)
                .and()
                .body(getBodyAuthUserRequest(email, password))
                .when()
                .post("/api/auth/login");

        unils.pojo.CreateUserResponse createUserResponse = response.body().as(unils.pojo.CreateUserResponse.class);
        return createUserResponse.getAccessToken();
    }

    @Step("Send POST  https://stellarburgers.nomoreparties.site/api/auth/login")
    public static Response sendPostRequestAutorization(String email, String password) {
        Response response = given()
                .spec(REQ_SPEC)
                .and()
                .body(getBodyAuthUserRequest(email, password))
                .when()
                .post("/api/auth/login");
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
