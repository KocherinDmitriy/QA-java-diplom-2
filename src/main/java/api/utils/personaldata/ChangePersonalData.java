package api.utils.personaldata;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ChangePersonalData {


    public static HashMap getBodyChangeUserCredentialsRequest(String email, String password, String name) {
        HashMap<String, Object> dataBody = new HashMap<String, Object>();
        dataBody.put("email", email);
        dataBody.put("password", password);
        dataBody.put("name", name);

        return dataBody;
    }
    private static final RequestSpecification REQ_SPEC=
            new RequestSpecBuilder()
                    .setBaseUri("https://stellarburgers.nomoreparties.site")
                    .setBasePath("/api/auth/user")
                    .setContentType(ContentType.JSON)
                    .build();

    @Step("Send POST https://stellarburgers.nomoreparties.site/api/auth/user")
    public static Response sendPatchRequestChangeUser(String authToken, String email, String password, String name) {
        Response response = given()
                .spec(REQ_SPEC).auth().oauth2(authToken)
                .and()
                .body(getBodyChangeUserCredentialsRequest(email,password,name))
                .when()
                .patch();
        ;
        return response;
    }
}