package api;

import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class ApiTest extends BaseApiTest {

    int commentId;

    @Test (priority = 0)
    public void myApiTest() throws IOException {

        Response response = baseUrl.
                path("/posts/621").
                request().
                get();

        String jsonResponse = response.readEntity(String.class);

        JsonNode jsonNode = mapper.readTree(jsonResponse);

        Assert.assertEquals(response.getStatus(), 200);
        Assert.assertEquals(jsonNode.get("title").asText(), "qweqweqwe");
        Assert.assertEquals(jsonNode.findValue("discussion").get("comment_count").asInt(), 0);
    }

    @Test (priority = 1)
    public void createNewComment() throws IOException {

        Form myform = new Form();
        myform.param("content", "API555");

        Response createComment = baseUrl.
                path("/posts/632/replies/new").
                request().
                header("Authorization", accessToken).
                header("Content-Type", "application/x-www-form-urlencoded").
                post(Entity.entity(myform, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        //System.out.println("createComment " + createComment.getStatus());
        Assert.assertEquals(createComment.getStatus(), 200, "Actual error message: " + createComment.readEntity(String.class));
    }

    @Test (priority = 2)
    public void getSingleComment() {

        Response getComment = baseUrl.
                path("/comments/" + commentId).
                request().
                get();

        System.out.println("getSingleComment " + getComment.getStatus());

    }

    @Test (priority = 3)
    public void deleteComment() {

        Response deleteComment = baseUrl.
                path("/comments/" + commentId + "/delete").
                request().
                header("Authorization", accessToken).
                post(null);

        System.out.println("deleteComment " + deleteComment.getStatus());

    }



}
