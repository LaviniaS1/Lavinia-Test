package bookstore;

import utils.PropertyReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteUserTest {

    @Test
    public void deleteUserTest() throws IOException {
        // Get the base URL and endpoint from the config file (assuming user ID is passed as part of URL)
        String userId = "your-user-id"; // Replace with actual user ID
        String url = PropertyReader.getProperty("bookstore.api.url") + "/Account/v1/User/" + userId;

        // Create an HTTP client
        CloseableHttpClient client = HttpClients.createDefault();

        // Create a DELETE request to the API endpoint
        HttpDelete request = new HttpDelete(url);

        // Send the request and get the response
        CloseableHttpResponse response = client.execute(request);

        // Assert the status code
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 204, "Expected status code 204"); // 204 indicates successful deletion with no content

        // Clean up and close the response
        response.close();
        client.close();
    }
}
