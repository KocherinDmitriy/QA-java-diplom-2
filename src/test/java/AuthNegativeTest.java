import api.utils.DeleteUser;
import api.utils.auth.Auth;
import api.utils.createuser.CreateUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AuthNegativeTest {

    static String generateEmail = String.format("%s@gmail.com", RandomStringUtils.randomAlphabetic(10));

    private final String email;
    private final String password;
    private final String message;

    public AuthNegativeTest(String email, String password, String message) {
        this.email = email;
        this.password = password;


        this.message = message;

    }

    @Parameterized.Parameters(name = "{index} => email={0}, password={1}, field={2}, message={3}, code={4}")
    // добавили аннотацию
    public static Object[][] getData() {
        return new Object[][]{
                {generateEmail, "",  "email or password are incorrect"},
                {"", "weqeqwe", "email or password are incorrect"},


        };
    }

    @DisplayName("User Authentication Negative Tests")
    @Description("No Email,No Password")
    @Test
    public void createNewCourierWithBusinessErrors() {

        Response response = new Auth().sendPostRequestAutorization(email,password);
        new CreateUser().compareResponse(response, "message", message);
        response.then().statusCode(401);
    }


}
