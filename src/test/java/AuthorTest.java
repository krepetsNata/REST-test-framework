import dataProviders.DataProviderAuthor;
import entity.ListOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Author;
import service.AuthorService;
import response.BaseResponse;
import utils.Validator;

import static org.apache.http.HttpStatus.*;

public class AuthorTest extends BaseTest {

    @Test(description = "Verifying creating new Author object with POST method",
            dataProvider = "dpTestAuthor", dataProviderClass = DataProviderAuthor.class)
    public void verifyCreatedAuthorPostRequest(Author author) {
        AuthorService authorService = new AuthorService();
        BaseResponse baseResponse = authorService.addAuthorPost(author);
        Validator.validateStatusCode(baseResponse, SC_CREATED);
        Validator.validateSameObjects(baseResponse, author);
    }

    @Test(description = "Verifying updating Author object with PUT method",
            dataProvider = "dpTestAuthor", dataProviderClass = DataProviderAuthor.class)
    public void verifyUpdatedAuthorPutRequest(Author author) {
        AuthorService authorService = new AuthorService();
        author.setAuthorDescription("updated author description");
        BaseResponse baseResponse = authorService.updateAuthorPut(author);
        Validator.validateStatusCode(baseResponse, SC_OK);
        Validator.validateSameObjects(baseResponse, author);
    }

    @Test(description = "Verifying getting 1 Author object by id with GET method",
            dataProvider = "dpTestAuthor", dataProviderClass = DataProviderAuthor.class)
    public void verifyGetAuthorByIdGetRequest(Author author) {
        AuthorService authorService = new AuthorService();
        BaseResponse baseResponse = authorService.getAuthorByAuthorIdGet(author.getAuthorId());
        Validator.validateStatusCode(baseResponse, SC_OK);
    }

    @Test(description = "Verifying getting all Author objects with GET method")
    public void verifyGetAllAuthorsGetRequest() {//verify that list contain more than 1 authors or list not empty
        AuthorService authorService = new AuthorService();
        BaseResponse baseResponse = authorService.getAuthors(new ListOptions());
        Validator.validateStatusCode(baseResponse, SC_OK);
    }

    @Test(description = "Verifying deleting 1 Author object by id with DELETE method",
            dataProvider = "dpTestAuthor", dataProviderClass = DataProviderAuthor.class)
    public void verifyDeleteAuthorByIdDeleteRequest(Author author) {
        AuthorService authorService = new AuthorService();
        BaseResponse baseResponse = authorService.deleteAuthorDelete(author.getAuthorId());

        Validator.validateStatusCode(baseResponse, SC_NO_CONTENT);

        baseResponse = authorService.getAuthorByAuthorIdGet(author.getAuthorId());
        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);
        Author author1 = (Author) Validator.getActualObject(baseResponse, author);
        Assert.assertEquals(author1.getAuthorId(), 0, "object exists");
    }

    @Test(description = "Negative case when element not found - verifying getting 1 Author object by id with GET method",
            dataProvider = "dpTestAuthor", dataProviderClass = DataProviderAuthor.class)
    public void verifyGetNotFoundAuthorByIdGetRequestNegativeCase(Author author) {
        AuthorService authorService = new AuthorService();
        author.setAuthorId(1113);
        BaseResponse baseResponse = authorService.getAuthorByAuthorIdGet(author.getAuthorId());

        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);

        Author authorActual = (Author) Validator.getActualObject(baseResponse, author);
        Assert.assertEquals(authorActual.getAuthorId(), 0, "object exists");
    }
}
