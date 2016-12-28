package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class ApiTest {

    Client client = ClientBuilder.newClient();
    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void myApiTest() throws IOException {

        Response response = client.
                target("https://public-api.wordpress.com/rest/v1.1/sites/sergeywebdrivertest.wordpress.com/posts/621").
                request().
                get();

        String jsonResponse = response.readEntity(String.class);

        JsonNode jsonNode = mapper.readTree(jsonResponse);

        Assert.assertEquals(response.getStatus(), 200);
        Assert.assertEquals(jsonNode.get("title").asText(), "qweqweqwe");
        Assert.assertEquals(jsonNode.findValue("discussion").get("comment_count").asInt(), 0);
    }


}
