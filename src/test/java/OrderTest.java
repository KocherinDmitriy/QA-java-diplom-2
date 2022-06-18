import api.utils.*;
import api.utils.orders.GenerateOrders;
import api.utils.orders.RequestOrders;
import api.utils.createuser.pojo.CreateAnswerPOJO;
import api.utils.createuser.CreateUser;
import api.utils.orders.pojo.Root;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class OrderTest {
    String login = String.format("%s@gmail.com", RandomStringUtils.randomAlphabetic(10));
    String password = RandomStringUtils.randomAlphabetic(10);
    String name = RandomStringUtils.randomAlphabetic(10);

    @DisplayName("Get All Orders test with auth user")
    @Description("Happy Path")
    @Test
    public void getAllOrderAuthUser() {

        CreateAnswerPOJO createAnswerPOJO = CreateUser.sendPostRequestCreateUser(login, password, name).body().as(CreateAnswerPOJO.class);
        Root root = RequestOrders.allOrders(createAnswerPOJO.getAccessToken()).body().as(Root.class);
        Assert.assertEquals(50, root.getOrders().size());

    }

    @DisplayName("Get Personal Orders test with auth user")
    @Description("Happy Path")
    @Test
    public void getMyOrders() {

        CreateAnswerPOJO createAnswerPOJO = CreateUser.sendPostRequestCreateUser(login, password, name).body().as(CreateAnswerPOJO.class);
        GenerateOrders generateOrders = new GenerateOrders();
        generateOrders.generate5Orders(createAnswerPOJO.getAccessToken(), "61c0c5a71d1f82001bdaaa6d");
        Root root = RequestOrders.allMyOrders(createAnswerPOJO.getAccessToken()).body().as(Root.class);
        ;
        System.out.println(root.getOrders().size());

    }

    @DisplayName("Get Personal Orders test without authorization")
    @Description("Negative")
    @Test
    public void getMyOrderWithoutAuth() {
        Response response1 = RequestOrders.allMyOrders("");
        response1.then().assertThat().body("success", equalTo(false));
        response1.then().assertThat().body("message", equalTo("You should be authorised"));

    }

    @DisplayName("Get All Orders test without authorization")
    @Description("Happy Path")
    @Test
    public void getAllOrderWithoutAuth() {
        Root root = RequestOrders.allOrders("").body().as(Root.class);
        Assert.assertEquals(50, root.getOrders().size());
        Assert.assertEquals(true, root.isSuccess());

    }

    @After
    public void teardown() {
        DeleteUser.sendDeleteRequestUser(login, password);
    }
}

