package bookstore;

import utils.PropertyReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserTest {

    @Test
    public void updateUserTest() throws IOException {
        // Get the base URL and endpoint from the config file
        String userId = "your-user-id"; // Replace with actual user ID
        String url = PropertyReader.getProperty("bookstore.api.url") + "/Account/v1/User/" + userId;

        // Create an HTTP client
        CloseableHttpClient client = HttpClients.createDefault();

        // Create a PUT request to the API endpoint
        HttpPut request = new HttpPut(url);

        // Set the request body (new user details)
        String jsonPayload = "{\"userName\": \"updatedUser\", \"password\": \"NewPassword@123\"}";
        StringEntity entity = new StringEntity(jsonPayload);
        request.setEntity(entity);

        // Set headers
        request.setHeader("Content-type", "application/json");

        // Send the request and get the response
        CloseableHttpResponse response = client.execute(request);

        // Assert the status code
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200");

        // Clean up and close the response
        response.close();
        client.close();
    }
}
