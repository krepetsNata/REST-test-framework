import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import entity.ListOptions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Author;
import pojo.AuthorBirth;
import pojo.AuthorName;
import pojoActions.AuthorService;
import response.BaseResponse;
import utils.PrettyJsonPrinting;

import java.util.List;

public class AuthorTest {

    Author author = new Author(1112, new AuthorBirth("Mykolaiv", "Ukraine", java.time.LocalDate.now()), "new author", new AuthorName("firstName", "secondName"), "ukrainian");

    @Test
    public void postRequest() {
        AuthorService authorService = new AuthorService();
        BaseResponse response = authorService.addAuthorPost(author);
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test
    public void putRequest() {
        AuthorService authorService = new AuthorService();
        author.setAuthorDescription("updated author descr");
        BaseResponse response = authorService.updateAuthorPut(author);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getRequest() {
        AuthorService authorService = new AuthorService();
        BaseResponse response = authorService.getAuthorByAuthorIdGet(author.getAuthorId());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getAllRequest() {
        AuthorService authorService = new AuthorService();
        BaseResponse response = authorService.getAuthors(new ListOptions());
        //System.out.println(PrettyJsonPrinting.getPrettyJsonString(response.getBody()));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void delRequest() {
        AuthorService authorService = new AuthorService();
        BaseResponse response = authorService.deleteAuthorDelete(author.getAuthorId());
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    @Test(description = "Negative case when element not found")
    public void getRequestNegativeCase() {
        AuthorService authorService = new AuthorService();
        author.setAuthorId(1113);
        BaseResponse response = authorService.getAuthorByAuthorIdGet(author.getAuthorId());
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
