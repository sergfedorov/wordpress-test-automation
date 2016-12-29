package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class FirstApitest {

    public static void main(String[] args) throws IOException {

        //Client client = ClientBuilder.newClient();
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

        Client client = ClientBuilder.newClient();
        ObjectMapper mapper = new ObjectMapper();

        Form form = new Form();
        form.param("content", "");

        WebTarget baseUrl = client.target("https://public-api.wordpress.com/rest/v1.1/sites/sergeywebdrivertest.wordpress.com");
        String accessToken = "Bearer rCBF9pkLOC13qit*Dm7n6A)QqQ)k^iSO@^$%u3sDQ(I2NoAVrfqjQ@FqS&8MI8Wg";


        Response createComment = baseUrl.path("/posts/632/replies/new").
                request().
                header("Authorization", accessToken).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        String myResponse1JSON = createComment.readEntity(String.class);

        System.out.println("createComment " + createComment.getStatus());
        System.out.println(myResponse1JSON);

/*        if(createComment.getStatus() != 200){
            System.out.println("Response code is not 200. Response code is: " + createComment.getStatus());
            System.out.println(myResponse1JSON);
            return;
        }

        JsonNode node = mapper.readTree(myResponse1JSON);
        int commentId = node.get("ID").asInt();
        System.out.println(commentId);


        Response getSingleComment = baseUrl.path("/comments/" + commentId).
                request().
                get();

        System.out.println("getSingleComment " + getSingleComment.getStatus());

        Response deleteComment = baseUrl.
                path("/comments/" + commentId + "/delete").
                request().
                header("Authorization", accessToken).
                post(null);

        System.out.println("deleteComment " + deleteComment.getStatus());

        Response getSingleCommentAfterDeletion = baseUrl.path("/comments/" + commentId).
                request().
                get();

        System.out.println("getSingleCommentAfterDeletion " + getSingleComment.getStatus());*/


    }
}
