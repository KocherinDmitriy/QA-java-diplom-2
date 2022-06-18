import api.utils.auth.Auth;
import api.utils.createuser.pojo.CreateAnswerPOJO;
import api.utils.createuser.CreateUser;
import api.utils.DeleteUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class AuthTest {
    String login = String.format("%s@gmail.com", RandomStringUtils.randomAlphabetic(10));
    String password = RandomStringUtils.randomAlphabetic(10);
    String name = RandomStringUtils.randomAlphabetic(10);

    @DisplayName("User Authentication")
    @Description("Happy Path")
    @Test
    public void authNewUserTest() {

        CreateAnswerPOJO createAnswerPOJO = new CreateUser().sendPostRequestCreateUser(login, password, name).body().as(CreateAnswerPOJO.class);
        Response response = new Auth().sendPostRequestAutorization(login,password);
        response.then().assertThat().body("success", equalTo(true));
        response.then().statusCode(200);


    }

    @After
    public void teardown() {
        new DeleteUser().sendDeleteRequestUser(login, password);
    }


}
