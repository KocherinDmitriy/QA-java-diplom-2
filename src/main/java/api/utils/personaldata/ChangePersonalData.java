package api.utils.personaldata;

import api.utils.BaseSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ChangePersonalData extends BaseSpec {


    public static HashMap getBodyChangeUserCredentialsRequest(String email, String password, String name) {
        HashMap<String, Object> dataBody = new HashMap<String, Object>();
        dataBody.put("email", email);
        dataBody.put("password", password);
        dataBody.put("name", name);

        return dataBody;
    }


    @Step("Send POST https://stellarburgers.nomoreparties.site/api/auth/user")
    public static Response sendPatchRequestChangeUser(String authToken, String email, String password, String name) {
        Response response = given()
                .spec(REQ_SPEC).auth().oauth2(authToken)
                .and()
                .body(getBodyChangeUserCredentialsRequest(email, password, name))
                .when()
                .patch("/api/auth/user");
        ;
        return response;
    }
}