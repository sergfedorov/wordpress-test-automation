package api;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;

public class BaseApiTest {

    protected int postId = 632;
    protected WebTarget baseUrl = ClientBuilder.
            newClient().
            target("https://public-api.wordpress.com/rest/v1.1/sites/sergeywebdrivertest.wordpress.com");
    protected String accessToken = "Bearer rCBF9pkLOC13qit*Dm7n6A)QqQ)k^iSO@^$%u3sDQ(I2NoAVrfqjQ@FqS&8MI8Wg";
    protected ObjectMapper mapper = new ObjectMapper();
    protected Form requestBody = new Form();


    protected void deleteComment(int commentId){
        Response deleteComment = baseUrl.
                path("/comments/"+commentId+"/delete").
                request().
                header("Authorization", accessToken).
                post(null);

        String deleteCommentJson = deleteComment.readEntity(String.class);

        if(deleteComment.getStatus() == 200){
            System.out.println("Comment has been deleted");
        } else {
            System.out.println("Comment has not been deleted. Error message: " + deleteCommentJson);
        }
    }

    protected int createComment() throws IOException {
        Response createComment = baseUrl.
                path("/posts/" + postId + "/replies/new").
                request().
                header("Authorization", accessToken).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(requestBody.param("content", "API Test " + LocalDateTime.now()),
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        String createCommentJson = createComment.readEntity(String.class);
        int commentID = mapper.readTree(createCommentJson).get("ID").asInt();
        return commentID;
    }



}
