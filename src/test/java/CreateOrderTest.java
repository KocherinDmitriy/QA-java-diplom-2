import api.utils.createuser.pojo.CreateAnswerPOJO;
import api.utils.ingridients.IngridientsRequest;
import api.utils.ingridients.pojo.IngridientBody;
import api.utils.orders.CreateOrder;
import api.utils.createuser.CreateUser;
import api.utils.DeleteUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;


public class CreateOrderTest {
    String login = String.format("%s@gmail.com", RandomStringUtils.randomAlphabetic(10));
    String password = RandomStringUtils.randomAlphabetic(10);
    String name = RandomStringUtils.randomAlphabetic(10);

    @DisplayName("Create Order with authorization and ingredients")
    @Description("Happy Path")

    @Test
    public void createOrder() {
        IngridientBody ingridientBody = new IngridientsRequest().getIngridientsRequest().body().as(IngridientBody.class);
        CreateAnswerPOJO createAnswerPOJO = new CreateUser().sendPostRequestCreateUser(login, password, name).body().as(CreateAnswerPOJO.class);
        Response response = CreateOrder.sendOrderRequest(createAnswerPOJO.getAccessToken(), ingridientBody.getData().get(5).get_id());
        CreateOrder.compareResponseWithBollean(response, "success", true);
        CreateOrder.compareResponse(response, "name", "Люминесцентный бургер");
        System.out.println(login + password);


    }

    @DisplayName("Create Order whith error in hash-code ingredient")
    @Description("Negative test")
    @Test
    public void createOrderWhithWrongHashIng() {
        CreateAnswerPOJO createAnswerPOJO = new CreateUser().sendPostRequestCreateUser(login, password, name).body().as(CreateAnswerPOJO.class);
        Response response = CreateOrder.sendOrderRequest(createAnswerPOJO.getAccessToken(), "609646e4dc916e00276b2870");
        CreateOrder.compareResponseWithBollean(response, "success", false);
        CreateOrder.compareResponse(response, "message", "One or more ids provided are incorrect");


    }

    @DisplayName("Create Order without authorization with ingredient")
    @Description("Happy Path")
    @Test
    public void createOrderWhithoutAutorization() {
        IngridientBody ingridientBody = new IngridientsRequest().getIngridientsRequest().body().as(IngridientBody.class);
        Response response = CreateOrder.sendOrderRequest("", ingridientBody.getData().get(1).get_id());
        CreateOrder.compareResponseWithBollean(response, "success", true);
        CreateOrder.compareResponse(response, "name", "Бессмертный бургер");
    }

    @DisplayName("Create Order without ingredient")
    @Description("Happy Path")
    @Test
    public void createOrderWithoutIngridients() {
        CreateAnswerPOJO createAnswerPOJO = new CreateUser().sendPostRequestCreateUser(login, password, name).body().as(CreateAnswerPOJO.class);
        Response response = CreateOrder.sendOrderRequestWitoutIngridients(createAnswerPOJO.getAccessToken());
        CreateOrder.compareResponseWithBollean(response, "success", false);
        CreateOrder.compareResponse(response, "message", "Ingredient ids must be provided");


    }

    @After
    public void teardown() {
        new DeleteUser().sendDeleteRequestUser(login, password);
    }

}
