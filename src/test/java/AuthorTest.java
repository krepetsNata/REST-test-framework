import dataProviders.DataProviderPOJO;
import entity.ListOptions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pojo.Author;
import pojo.Book;
import pojo.Genre;
import service.AuthorService;
import response.BaseResponse;
import utils.Validator;

import java.util.List;

import static org.apache.http.HttpStatus.*;

@Feature("Author api test")
public class AuthorTest extends BaseTest {

    AuthorService authorService = new AuthorService();

    @Test(description = "Verifying creating new Author object with POST method",
            dataProvider = "dpNewAuthor", dataProviderClass = DataProviderPOJO.class,
            groups = "authorGroup")
    public void verifyCreatingAuthorPostRequest(Author author) {
        BaseResponse<Author> baseResponse = authorService.addAuthorPost(author);
        Validator.validateStatusCode(baseResponse, SC_CREATED);
        Validator.validateSameObjects(baseResponse, author);
    }

    @Test(description = "Verifying updating Author object with PUT method",
            dataProvider = "dpNewAuthor", dataProviderClass = DataProviderPOJO.class,
            groups = "authorGroup")
    public void verifyUpdatingAuthorPutRequest(Author author) {
        author.setAuthorDescription("updated author description");
        BaseResponse<Author> baseResponse = authorService.updateAuthorPut(author);
        Validator.validateStatusCode(baseResponse, SC_OK);
        Validator.validateSameObjects(baseResponse, author);
    }

    @Test(description = "Verifying getting 1 Author object by id with GET method",
            dataProvider = "dpTestAuthor", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetAuthorByIdGetRequest(Author author) {
        BaseResponse<Author> baseResponse = authorService.getAuthorByAuthorIdGet(author.getAuthorId());
        Validator.validateStatusCode(baseResponse, SC_OK);
    }

    @Test(description = "Verifying getting all Author objects with GET method",
            groups = "withoutPreconditionGroup")
    public void verifyGetAllAuthorsGetRequest() {
        BaseResponse<Author> baseResponse = authorService.getAuthors(new ListOptions());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Author> authorsActualList = authorService.getActualListAuthors(baseResponse);
        Validator.validateListIsEmpty(authorsActualList, false);
    }

    @Test(description = "Verifying deleting 1 Author object by id with DELETE method",
            dataProvider = "dpNewAuthor", dataProviderClass = DataProviderPOJO.class,
            groups = "authorGroup")
    public void verifyDeletingAuthorByIdDeleteRequest(Author author) {
        BaseResponse<Author> baseResponse = authorService.deleteAuthorDelete(author.getAuthorId());
        Validator.validateStatusCode(baseResponse, SC_NO_CONTENT);
        baseResponse = authorService.getAuthorByAuthorIdGet(author.getAuthorId());
        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);
        Author authorActual = authorService.getActualObjAuthor(baseResponse);
        Validator.validateObjectIsNull(authorActual, true);
    }

    @Test(description = "Negative case when element not found - verifying getting 1 Author object by id with GET method",
            dataProvider = "dpNewAuthor", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetNotFoundAuthorByIdGetRequestNegativeCase(Author author) {
        BaseResponse<Author> baseResponse = authorService.getAuthorByAuthorIdGet(author.getAuthorId());
        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);
        Author authorActual = authorService.getActualObjAuthor(baseResponse);
        Validator.validateObjectIsNull(authorActual, true);
    }

    @Test(description = "Verifying getting 1 Author object by Book id with GET method",
            dataProvider = "dpTestBook", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetAuthorByBookIdGetRequest(Book book) {
        BaseResponse<Author> baseResponse = authorService.getAuthorByBookIdGet(new ListOptions(), book.getBookId());
        Validator.validateStatusCode(baseResponse, SC_OK);
    }

    @Test(description = "Verifying getting 1 Author object by Book id with GET method",
            dataProvider = "dpTestGenre", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetAuthorsByGenreIdGetRequest(Genre genre) {
        BaseResponse<Author> baseResponse = authorService.getAuthorsByGenreIdGet(new ListOptions(), genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_OK);
    }

    @Test(description = "Verifying searching books by its name",
            groups = "withoutPreconditionGroup")
    @Description("Verify searching books by its name")
    public void verifySearchAuthorsGetRequest() {//add DP for searching
        BaseResponse<Author> baseResponse = authorService.getAuthorsSearchGet(new ListOptions(), "Sch");
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Author> authorsActualList = authorService.getActualListAuthors(baseResponse);
        Validator.validateListIsEmpty(authorsActualList, false);
    }
}
