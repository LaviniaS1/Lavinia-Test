package bookstore;

import utils.PropertyReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateUserTest {

    @Test
    public void createUserTest() throws IOException {
        // Get the base URL and endpoint from the config file
        String url = PropertyReader.getProperty("bookstore.api.url") + PropertyReader.getProperty("bookstore.api.createUser.endpoint");

        // Create an HTTP client
        CloseableHttpClient client = HttpClients.createDefault();

        // Create a POST request to the API endpoint
        HttpPost request = new HttpPost(url);

        // Set the request body (user details)
        String jsonPayload = "{\"Lavi\": \"Lavinia\", \"Test123\": \"Test@1234\"}";
        StringEntity entity = new StringEntity(jsonPayload);
        request.setEntity(entity);

        // Set headers
        request.setHeader("Content-type", "application/json");

        // Send the request and get the response
        CloseableHttpResponse response = client.execute(request);

        // Assert the status code
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 201, "Expected status code 201");

        // Clean up and close the response
        response.close();
        client.close();
    }
}
