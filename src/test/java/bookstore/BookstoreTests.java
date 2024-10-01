package bookstore;

import config.PropertyReader;
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

        String url = PropertyReader.getProperty("bookstore.api.url") + PropertyReader.getProperty("bookstore.api.books.endpoint");

        //HTTP client
        CloseableHttpClient client = HttpClients.createDefault();

        //GET request to the API endpoint
        HttpGet request = new HttpGet(url);

        //Send the request and get the response
        CloseableHttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200");

        response.close();
        client.close();
    }
}
