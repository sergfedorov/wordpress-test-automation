package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;

public class CreateCommentTest extends BaseApiTest{

    @Test
    public void createNewComment() throws IOException {

        Response createComment = baseUrl.
                path("/posts/"+postId+"/replies/new").
                request().
                header("Authorization", accessToken).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(requestBody.param("content", "API Test " + LocalDateTime.now()),
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        String createCommentJson = createComment.readEntity(String.class);

        Assert.assertEquals(createComment.getStatus(), 200, "Actual error message: " + createCommentJson);

        int commentID = mapper.readTree(createCommentJson).get("ID").asInt();
        super.deleteComment(commentID);
    }

    @Test
    public void createDuplicateCommentExpectedError409(){

        Response createComment = baseUrl.
                path("/posts/"+postId+"/replies/new").
                request(MediaType.APPLICATION_JSON).
                header("Authorization", accessToken).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(requestBody.param("content", "API02"),
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        String createCommentJson = createComment.readEntity(String.class);

        Assert.assertEquals(createComment.getStatus(), 409);
        Assert.assertEquals(createCommentJson, "{\"error\":\"comment_duplicate\",\"message\":\"Duplicate comment detected; it looks as though you&#8217;ve already said that!\"}");
    }

    @Test
    public void createEmptyCommentExpectedError400(){

        Response createComment = baseUrl.
                path("/posts/"+postId+"/replies/new").
                request(MediaType.APPLICATION_JSON).
                header("Authorization", accessToken).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(requestBody.param("content", ""),
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        String createCommentJson = createComment.readEntity(String.class);

        Assert.assertEquals(createComment.getStatus(), 400);
        Assert.assertEquals(createCommentJson, "{\"error\":\"invalid_input\",\"message\":\"Invalid request input\"}");
    }

    @Test
    public void createCommentNotAuthorizedExpectedError403(){

        Response createComment = baseUrl.
                path("/posts/"+postId+"/replies/new").
                request(MediaType.APPLICATION_JSON).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(requestBody.param("content", "API test"),
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        String createCommentJson = createComment.readEntity(String.class);
        //System.out.println(createCommentJson);

        Assert.assertEquals(createComment.getStatus(), 403);
        Assert.assertEquals(createCommentJson, "{\"error\":\"authorization_required\",\"message\":\"An active access token must be used to comment.\"}");
    }

}
