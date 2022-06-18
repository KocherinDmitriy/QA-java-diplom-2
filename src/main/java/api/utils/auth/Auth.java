package api.utils.auth;

import api.utils.BaseSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Auth extends BaseSpec {
    public Auth() {
    }

    private HashMap getBodyAuthUserRequest(String email, String password) {
        HashMap<String, Object> dataBody = new HashMap<String, Object>();
        dataBody.put("email", email);
        dataBody.put("password", password);


        return dataBody;
    }


    public  String getAuthToken(String email, String password) {
        Response response = given()
                .spec(REQ_SPEC)
                .and()
                .body(getBodyAuthUserRequest(email, password))
                .when()
                .post("/api/auth/login");

        api.utils.personaldata.answercreateuser.CreateUserResponse createUserResponse = response.body().as( api.utils.personaldata.answercreateuser.CreateUserResponse.class);
        return createUserResponse.getAccessToken();
    }

    @Step("Send POST  https://stellarburgers.nomoreparties.site/api/auth/login")
    public  Response sendPostRequestAutorization(String email, String password) {
        Response response = given()
                .spec(REQ_SPEC)
                .and()
                .body(getBodyAuthUserRequest(email, password))
                .when()
                .post("/api/auth/login");
        ;
        return response;
    }

}
