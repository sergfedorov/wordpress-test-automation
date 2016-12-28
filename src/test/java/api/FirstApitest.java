package api;

import javax.ws.rs.client.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FirstApitest {

    public static void main(String[] args) throws IOException {

        Client client = ClientBuilder.newClient();
/*        WebTarget target = client.target("https://public-api.wordpress.com/rest/v1.1/sites/sergeywebdrivertest.wordpress.com/posts/621");
        String result1 = target.request().get(String.class);

        Invocation.Builder request1 = target.request().accept(MediaType.APPLICATION_JSON_TYPE);

        Response response = target.request().get();

        String rp = response.readEntity(String.class);

        String myResponse = ClientBuilder.newClient().
                target("https://public-api.wordpress.com/rest/v1.1/sites/sergeywebdrivertest.wordpress.com/posts/621").
                request().
                get().
                readEntity(String.class);

        System.out.println("getAllowedMethods " + response.getAllowedMethods());
        System.out.println("getCookies " + response.getCookies());
        System.out.println("getDate " + response.getDate());
        System.out.println("getHeaderString " + response.getHeaderString("server"));
        System.out.println("getStatus " + response.getStatus());
        System.out.println(response.readEntity(String.class));



        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(rp);

        System.out.println("find path " + node.get("ID"));*/

        Map<String, String> map = new HashMap<String, String>();
        map.put("content", "xx");

        String myResponse1 = ClientBuilder.newClient().
                target("https://public-api.wordpress.com/rest/v1.1/sites/sergeywebdrivertest.wordpress.com/posts/632/replies/new").
                request().
                header("Authorization", "Bearer rCBF9pkLOC13qit*Dm7n6A)QqQ)k^iSO@^$%u3sDQ(I2NoAVrfqjQ@FqS&8MI8Wg").
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.text(map)).
                readEntity(String.class);


        System.out.println(myResponse1);

    }
}
