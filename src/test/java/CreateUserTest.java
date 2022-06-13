import api.utils.DeleteUser;
import api.utils.createuser.CreateUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

public class CreateUserTest {

    String login = String.format("%s@gmail.com", RandomStringUtils.randomAlphabetic(10));
    String password = RandomStringUtils.randomAlphabetic(10);
    String name = RandomStringUtils.randomAlphabetic(10);

    @DisplayName("Create Uniq User")
    @Description("Happy Path")

    @Test
    public void createNewCourierWithRandomLogPass() {


        Response response = CreateUser.sendPostRequestCreateUser(login, password, name);
        CreateUser.compareResponseWithBollean(response, "success", true);
        response.then().statusCode(200);
        System.out.println(login + " " + password);
    }

    @After
    public void teardown() {
        DeleteUser.sendDeleteRequestUser(login, password);
    }


}
