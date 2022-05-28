package api.utils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    public Response sendDeleteRequestUser(String authToken) {
        Response response = given()
                .header("Content-type", "application/json").auth().oauth2(authToken)
                .and()
                .body("")
                .when()
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user");
        ;
        return response;
    }

}
