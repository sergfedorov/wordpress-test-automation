package api;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class BaseApiTest {

    protected WebTarget webResource = ClientBuilder.newClient().target("https://public-api.wordpress.com/rest/v1.1/sites/sergeywebdrivertest.wordpress.com");
    protected ObjectMapper mapper = new ObjectMapper();
    protected String accessToken = "Bearer rCBF9pkLOC13qit*Dm7n6A)QqQ)k^iSO@^$%u3sDQ(I2NoAVrfqjQ@FqS&8MI8Wg";
    protected Form requestBody = new Form();


    protected void deleteComment(int commentId){
        Response deleteComment = webResource.
                path("/comments/" + commentId + "/delete").
                request().
                header("Authorization", accessToken).
                post(null);

        String deleteCommentJson = deleteComment.readEntity(String.class);

        if(deleteComment.getStatus() == 200){
            System.out.println("Comment has been deleted");
        } else {
            System.out.println("Comment has nott been deleted. Error message: " + deleteCommentJson);
        }
    }



}
