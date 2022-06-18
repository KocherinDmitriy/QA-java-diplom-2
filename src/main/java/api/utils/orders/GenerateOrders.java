package api.utils.orders;

import io.restassured.response.Response;

public class GenerateOrders {

    public void generate5Orders(String authToken, String ingridient) {
        int cycles = 0;
        do {
            Response response = new CreateOrder().sendOrderRequest(authToken, ingridient);
            cycles++;
        } while (cycles < 5);
    }
}
