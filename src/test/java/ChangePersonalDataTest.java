import api.utils.*;
import api.utils.auth.Auth;
import api.utils.createuser.pojo.CreateAnswerPOJO;
import api.utils.createuser.CreateUser;
import api.utils.personaldata.ChangePersonalData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class ChangePersonalDataTest {

    String login = String.format("%s@gmail.com", RandomStringUtils.randomAlphabetic(10));
    String password = RandomStringUtils.randomAlphabetic(10);
    String name = RandomStringUtils.randomAlphabetic(10);
    String loginToChange = String.format("%s@gmail.com", RandomStringUtils.randomAlphabetic(10));
    String passwordToChange = RandomStringUtils.randomAlphabetic(10);
    String nameToChange = RandomStringUtils.randomAlphabetic(10);

    @DisplayName("Change Personal Data with authorization")
    @Description("Happy Path")
    @Test
    public void changePersonalData() {


        CreateAnswerPOJO createAnswerPOJO = new CreateUser().sendPostRequestCreateUser(login, password, name).body().as(CreateAnswerPOJO.class);
        Response response = new ChangePersonalData().sendPatchRequestChangeUser(createAnswerPOJO.getAccessToken(), loginToChange, passwordToChange, nameToChange);
        response.then().assertThat().body("success", equalTo(true));
        response.then().statusCode(200);
        response.then().assertThat().equals(loginToChange);
        response.then().assertThat().equals(nameToChange);
    }

    @DisplayName("Change Personal Data with authorization and check confirmed new Password")
    @Description("Happy Path")
    @Test
    public void authWithNewPersonalData() { //check password

        CreateAnswerPOJO createAnswerPOJO = new CreateUser().sendPostRequestCreateUser(login, password, name).body().as(CreateAnswerPOJO.class);
        Response response = new ChangePersonalData().sendPatchRequestChangeUser(createAnswerPOJO.getAccessToken(), loginToChange, passwordToChange, nameToChange);
        Response responseOldData = new Auth().sendPostRequestAutorization(login,password);
        responseOldData.then().assertThat().body("success", equalTo(false));
        Response responseNewData = new Auth().sendPostRequestAutorization(loginToChange,passwordToChange);
        responseNewData.then().assertThat().body("success", equalTo(true));
        responseNewData.then().statusCode(200);
        responseNewData.then().assertThat().equals(loginToChange);
        responseNewData.then().assertThat().equals(nameToChange);
    }

    @DisplayName("Change Personal Data without  authorization")
    @Description("Negative")
    @Test
    public void changePersonalDataWhitBadAccessToken() {

        Response response = new ChangePersonalData().sendPatchRequestChangeUser("", loginToChange, passwordToChange, nameToChange);
        response.then().statusCode(401);
        response.then().assertThat().body("message", equalTo("You should be authorised"));
    }

    @After
    public void teardown() {
        new DeleteUser().sendDeleteRequestUser(loginToChange, passwordToChange);
    }

}
