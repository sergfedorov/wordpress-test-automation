package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;

public class FirstApitest extends BaseApiTest{

    @Test
    public void editComment() throws IOException {

        int commentId = super.createComment();
        System.out.println(super.getSingleComment(commentId));

        Response editComment = baseUrl.
                path("/comments/"+commentId).
                request(MediaType.APPLICATION_JSON).
                header("Authorization", accessToken).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(requestBody.param("content", "API Test UPDATE " + LocalDateTime.now()),
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        String editCommentJson = editComment.readEntity(String.class);
        System.out.println(editCommentJson);

        /*Assert.assertEquals(editComment.getStatus(), 200);
        String commentContent = mapper.readTree(editCommentJson).get("content").asText();
        Assert.assertTrue(commentContent.contains("API Test UPDATE"));*/

        super.deleteComment(commentId);
    }

}
