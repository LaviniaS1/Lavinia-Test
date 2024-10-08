package bookstore;

import utils.PropertyReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class BookstoreTests {

    @Test
    public void getBooksTest() throws IOException {
        // Get the base URL and endpoint from the config file
        String url = PropertyReader.getProperty("bookstore.api.url") + PropertyReader.getProperty("bookstore.api.books.endpoint");

        // Create an HTTP client
        CloseableHttpClient client = HttpClients.createDefault();

        // Create a GET request to the API endpoint
        HttpGet request = new HttpGet(url);

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
