import api.utils.auth.Auth;
import api.utils.createuser.CreateUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AuthNegativeTest {

    static String generateEmail=String.format("%s@gmail.com", RandomStringUtils.randomAlphabetic(10));

    private final String email;
    private final String password;
    private final String field;
    private final String message;
    private final int code;

    public AuthNegativeTest(String email, String password, String field, String message, int code) {
        this.email = email;
        this.password = password;

        this.field = field;
        this.message = message;
        this.code = code;
    }

    @Parameterized.Parameters (name = "{index} => email={0}, password={1}, field={2}, message={3}, code={4}") // добавили аннотацию
    public static Object[][] getData() {
        return new Object[][]{
                {generateEmail,"", "message", "email or password are incorrect", 401},
                {"", "weqeqwe", "message", "email or password are incorrect", 401},


        };
    }

    @DisplayName("User Authentication Negative Tests")
    @Description("No Email,No Password")
    @Test
    public void createNewCourierWithBusinessErrors() {

        Response response = Auth.sendPostRequestAutorization(email, password);
        CreateUser.compareResponse(response, field, message);
        response.then().statusCode(code);
    }

}
