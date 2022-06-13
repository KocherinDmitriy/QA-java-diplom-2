package api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static api.utils.auth.Auth.getAuthToken;
import static io.restassured.RestAssured.given;

public class DeleteUser {

    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://stellarburgers.nomoreparties.site")
                    .setBasePath("/api/auth/user")
                    .setContentType(ContentType.JSON)
                    .build();

    public static void sendDeleteRequestUser(String login, String password) {
        String oauthToken = getAuthToken(login, password);
        if (oauthToken == null) {
            System.out.println("Пользователя нет совсем");
        } else {
            given()
                    .spec(REQ_SPEC)
                    .and()
                    .body("")
                    .when()
                    .delete();
        }
    }


}
