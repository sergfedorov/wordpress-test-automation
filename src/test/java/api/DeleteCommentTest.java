package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;

public class DeleteCommentTest extends BaseApiTest {

    @Test
    public void trashComment() throws IOException {

        int commentId = super.createComment();
        Response trashComment = baseUrl.
                path("/comments/" + commentId + "/delete").
                request().
                header("Authorization", accessToken).
                post(null);

        String trashCommentJson = trashComment.readEntity(String.class);

        Assert.assertEquals(trashComment.getStatus(), 200);
        String commentStatus = mapper.readTree(trashCommentJson).get("status").asText();
        Assert.assertEquals(commentStatus, "trash");
    }

    @Test
    public void deleteComment() throws IOException {

        int commentId = super.createComment();
        super.deleteComment(commentId);

        Response deleteComment = baseUrl.
                path("/comments/" + commentId + "/delete").
                request().
                header("Authorization", accessToken).
                post(null);

        String deleteCommentJson = deleteComment.readEntity(String.class);

        Assert.assertEquals(deleteComment.getStatus(), 200);

        String commentStatus = mapper.readTree(deleteCommentJson).get("status").asText();
        Assert.assertEquals(commentStatus, "deleted");
    }

    @Test
    public void deleteNotExistingCommentExpectedError404() throws IOException {

        int commentId = super.createComment();
        super.deleteComment(commentId);
        super.deleteComment(commentId);

        Response deletePermanentlyComment = baseUrl.
                path("/comments/" + commentId + "/delete").
                request().
                header("Authorization", accessToken).
                post(null);

        String deletePermanentlyCommentJson = deletePermanentlyComment.readEntity(String.class);

        //System.out.println(deleteCommentJson);

        Assert.assertEquals(deletePermanentlyComment.getStatus(), 404);
        Assert.assertEquals(deletePermanentlyCommentJson, "{\"error\":\"unknown_comment\",\"message\":\"Unknown comment\"}");

        super.deleteComment(commentId);
    }

    @Test
    public void trashCommentNotAuthorizedExpectedError403() throws IOException {

        int commentId = super.createComment();
        Response trashComment = baseUrl.
                path("/comments/" + commentId + "/delete").
                request().
                post(null);

        String trashCommentJson = trashComment.readEntity(String.class);

        Assert.assertEquals(trashComment.getStatus(), 403);
        Assert.assertEquals(trashCommentJson, "{\"error\":\"unauthorized\",\"message\":\"User cannot delete comment\"}");
        super.deleteComment(commentId);
    }

}
