package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;

public class GetSingleComment extends BaseApiTest{

    @Test
    public void getSingleComment() throws IOException {

        int commentId = super.createComment();
        Response getSingleComment = baseUrl.
                path("/comments/"+commentId).
                request(MediaType.APPLICATION_JSON).
                get();

        String getSingleCommentJson = getSingleComment.readEntity(String.class);
        System.out.println(getSingleCommentJson);

        Assert.assertEquals(getSingleComment.getStatus(), 200);

        super.deleteComment(commentId);
    }

    @Test
    public void getNotExistingSingleCommentExpectedError404() throws IOException {

        //int commentId = super.createComment();
        Response getSingleComment = baseUrl.
                path("/comments/456465").
                request(MediaType.APPLICATION_JSON).
                get();

        String getSingleCommentJson = getSingleComment.readEntity(String.class);
        //System.out.println(getSingleCommentJson);

        Assert.assertEquals(getSingleComment.getStatus(), 404);
        Assert.assertEquals(getSingleCommentJson, "{\"error\":\"unknown_comment\",\"message\":\"Unknown comment\"}");
    }



}
