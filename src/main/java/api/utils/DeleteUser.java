package api.utils;

import api.utils.auth.Auth;

import static io.restassured.RestAssured.given;

public class DeleteUser extends BaseSpec {
    public DeleteUser() {
    }

    public void sendDeleteRequestUser(String login, String password) {
        String oauthToken = new Auth().getAuthToken(login, password);
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
