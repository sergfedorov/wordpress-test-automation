package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditCommentTest extends BaseApiTest{

    @Test
    public void editComment() throws IOException {

        int commentId = super.createComment();
        Response editComment = baseUrl.
                path("/comments/"+commentId).
                request(MediaType.APPLICATION_JSON).
                header("Authorization", accessToken).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(requestBody.param("content", "API Test UPDATE " + LocalDateTime.now()),
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        String editCommentJson = editComment.readEntity(String.class);
        System.out.println(editCommentJson);

        Assert.assertEquals(editComment.getStatus(), 200);
        String commentContent = mapper.readTree(editCommentJson).get("content").asText();
        Assert.assertTrue(commentContent.contains("API Test UPDATE"));

        super.deleteComment(commentId);
    }

    // not an error!??
    @Test
    public void editWithEmptyDataComment() throws IOException {

        int commentId = super.createComment();
        Response editComment = baseUrl.
                path("/comments/"+commentId).
                request(MediaType.APPLICATION_JSON).
                header("Authorization", accessToken).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(requestBody.param("content", ""),
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        String editCommentJson = editComment.readEntity(String.class);
        System.out.println(editCommentJson);

        /*Assert.assertEquals(editComment.getStatus(), 200);
        String commentContent = mapper.readTree(editCommentJson).get("content").asText();
        Assert.assertTrue(commentContent.contains("API Test UPDATE"));*/

        //super.deleteComment(commentId);
    }

    @Test
    public void editNotExistingCommentExpectedError404() throws IOException {

        //int commentId = super.createComment();
        Response editNotExistingComment = baseUrl.
                //path("/comments/"+commentId).
                path("/comments/456123").
                request(MediaType.APPLICATION_JSON).
                header("Authorization", accessToken).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(requestBody.param("content", "API Test UPDATE 123"),
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        String editNotExistingCommenttJson = editNotExistingComment.readEntity(String.class);
        System.out.println(editNotExistingCommenttJson);

        Assert.assertEquals(editNotExistingComment.getStatus(), 404);
        Assert.assertEquals(editNotExistingCommenttJson, "{\"error\":\"unknown_comment\",\"message\":\"Unknown comment\"}");
    }

    @Test
    public void editCommentNotAuthorizedExpectedError403() throws IOException {

        int commentId = super.createComment();
        Response editComment = baseUrl.
                path("/comments/"+commentId).
                request(MediaType.APPLICATION_JSON).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(requestBody.param("content", "API Test UPDATE " + LocalDateTime.now()),
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        String editCommentJson = editComment.readEntity(String.class);
        System.out.println(editCommentJson);

        Assert.assertEquals(editComment.getStatus(), 403);
        Assert.assertEquals(editCommentJson, "{\"error\":\"unauthorized\",\"message\":\"User cannot edit comment\"}");

        super.deleteComment(commentId);
    }
}
