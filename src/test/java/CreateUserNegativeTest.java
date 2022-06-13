import api.utils.DeleteUser;
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
public class CreateUserNegativeTest {
    static String generateEmail = String.format("%s@gmail.com", RandomStringUtils.randomAlphabetic(10));

    private final String email;
    private final String password;
    private final String name;
    private final String field;
    private final String message;
    private final int code;

    public CreateUserNegativeTest(String email, String password, String name, String field, String message, int code) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.field = field;
        this.message = message;
        this.code = code;
    }

    @Parameterized.Parameters(name = "{index} => email={0}, password={1}, name={2}, field={3}, message={4}, code={5}")
    // добавили аннотацию
    public static Object[][] getData() {
        return new Object[][]{
                {"", "aasdad1", "asdasd", "message", "Email, password and name are required fields", 403},
                {generateEmail, "", "asdasd", "message", "Email, password and name are required fields", 403},
                {generateEmail, "weqeqwe", "", "message", "Email, password and name are required fields", 403},
                {"test-data@yandex.ru", "aasdad", "asdasd", "message", "User already exists", 403},

        };
    }

    @DisplayName("Create  User Negative Test") // имя теста
    @Description("No Email,No Password,No Name, User Already in use ") // описание теста
    @Test
    public void createNewCourierWithBusinessErrors() {

        Response response = CreateUser.sendPostRequestCreateUser(email, password, name);
        CreateUser.compareResponse(response, field, message);
        response.then().statusCode(code);
    }

    @After
    public void teardown() {
        DeleteUser.sendDeleteRequestUser(email, password);
    }

}
