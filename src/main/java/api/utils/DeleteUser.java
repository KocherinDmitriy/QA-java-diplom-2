package api.utils;


import static api.utils.auth.Auth.getAuthToken;
import static io.restassured.RestAssured.given;

public class DeleteUser extends BaseSpec {

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
                    .delete("/api/auth/user");
        }
    }


}
