//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HttpRequest stepRequest = HttpRequest.HttpRequestStepBuilder.getBuilder()
                .withUrl("https://api.example.com/products")
                .withMethod("POST")
                .withHeader("Content-Type", "application/json")
                .withBody("{\"product\": \"Laptop\", \"price\": 49999}")
                .withTimeout(45)
                .build();

        stepRequest.execute();
    }
}