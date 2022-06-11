package api.utils.createuser;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUser {

    public static HashMap getBodyCreateUserRequest(String email, String password, String name) {
        HashMap<String, Object> dataBody = new HashMap<String, Object>();
        dataBody.put("email", email);
        dataBody.put("password", password);
        dataBody.put("name", name);

        return dataBody;
    }
    private static final RequestSpecification REQ_SPEC=
            new RequestSpecBuilder()
                    .setBaseUri("https://stellarburgers.nomoreparties.site")
                    .setBasePath("/api/auth/register")
                    .setContentType(ContentType.JSON)
                    .build();

    @Step("Send POST https://stellarburgers.nomoreparties.site/api/auth/register")
    public static Response sendPostRequestCreateUser(String email, String password, String name) {
        Response response = given()
                .spec(REQ_SPEC)
                .and()
                .body(getBodyCreateUserRequest(email,password,name))
                .when()
                .post();
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
