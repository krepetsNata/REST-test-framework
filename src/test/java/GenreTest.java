import dataProviders.DataProviderPOJO;
import entity.ListOptions;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import pojo.Author;
import pojo.Book;
import pojo.Genre;
import response.BaseResponse;
import service.GenreService;
import utils.Validator;

import java.util.List;

import static org.apache.http.HttpStatus.*;

public class GenreTest extends BaseTest {

    GenreService genreService = new GenreService();

    @Test(description = "Verifying creating new Genre object with POST method",
            dataProvider = "dpNewGenre", dataProviderClass = DataProviderPOJO.class)
    public void verifyCreatingGenrePostRequest(Genre genre) {
        BaseResponse<Genre> baseResponse = genreService.addGenrePost(genre);
        Validator.validateStatusCode(baseResponse, SC_CREATED);
        Validator.validateSameObjects(baseResponse, genre);
        genreService.deleteGenreDelete(genre.getGenreId());
    }

    @Test(description = "Verifying updating Genre object with PUT method",
            dataProvider = "dpNewGenre", dataProviderClass = DataProviderPOJO.class)
    public void verifyUpdatingGenrePutRequest(Genre genre) {
        genreService.addGenrePost(genre);
        genre.setGenreDescription("updated genre description");
        BaseResponse<Genre> baseResponse = genreService.updateGenrePut(genre);
        Validator.validateStatusCode(baseResponse, SC_OK);
        Validator.validateSameObjects(baseResponse, genre);
        genreService.deleteGenreDelete(genre.getGenreId());
    }

    @Test(description = "Verifying getting 1 Genre object by id with GET method",
            dataProvider = "dpTestGenre", dataProviderClass = DataProviderPOJO.class)
    public void verifyGetGenreByIdGetRequest(Genre genre) {
        BaseResponse<Genre> baseResponse = genreService.getGenreByGenreIdGet(genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_OK);
    }

    @Test(description = "Verifying getting all Genre objects with GET method")
    public void verifyGetAllGenresGetRequest() {
        BaseResponse<Genre> baseResponse = genreService.getGenres(new ListOptions());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Genre> genreActualList = genreService.getActualListGenres(baseResponse);
        Assert.assertFalse(genreActualList.isEmpty(), "List of Genres is empty");
    }

    @Test(description = "Verifying deleting 1 Genre object by id with DELETE method",
            dataProvider = "dpNewGenre", dataProviderClass = DataProviderPOJO.class)
    public void verifyDeletingGenreByIdDeleteRequest(Genre genre) {
        genreService.addGenrePost(genre);
        BaseResponse<Genre> baseResponse = genreService.deleteGenreDelete(genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_NO_CONTENT);
        baseResponse = genreService.getGenreByGenreIdGet(genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);
        Genre genreActual = genreService.getActualObjGenre(baseResponse);
        Assert.assertEquals(genreActual.getGenreId(), 0, "object exists");
    }

    @Test(description = "Negative case when element not found - verifying getting 1 Genre object by id with GET method",
            dataProvider = "dpNewGenre", dataProviderClass = DataProviderPOJO.class)
    public void verifyGetNotFoundGenreByIdGetRequestNegativeCase(Genre genre) {
        BaseResponse<Genre> baseResponse = genreService.getGenreByGenreIdGet(genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);
        Genre genreActual = genreService.getActualObjGenre(baseResponse);
        Assert.assertEquals(genreActual.getGenreId(), 0, "object exists");
    }

    @Test(description = "Verifying getting all Genre objects by AuthorId with GET method",
            dataProvider = "dpTestAuthor", dataProviderClass = DataProviderPOJO.class)
    public void verifyGetAllGenresByAuthorIdGetRequest(Author author) {
        BaseResponse<Genre> baseResponse = genreService.getGenresByAuthorIdGet(new ListOptions(),author.getAuthorId());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Genre> genreActualList = genreService.getActualListGenres(baseResponse);
        Assert.assertFalse(genreActualList.isEmpty(), "List of Genres is empty");
    }

    @Test(description = "Verifying getting all Genre objects by BookId with GET method",
            dataProvider = "dpTestBook", dataProviderClass = DataProviderPOJO.class)
    public void verifyGetGenresByBookIdGetRequest(Book book) {
        BaseResponse<Genre> baseResponse = genreService.getGenresByBookIdGet(new ListOptions(), book.getBookId());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Genre> genreActualList = genreService.getActualListGenres(baseResponse);
        Assert.assertFalse(genreActualList.isEmpty(), "List of Genres is empty");
    }

    @Test(description = "Verifying searching Genres by its name")
    @Description("Verify searching Genres by its name")
    public void verifySearchGenresGetRequest() {//add DP for serching
        BaseResponse<Genre> baseResponse = genreService.getGenresSearchGet(new ListOptions(), "Myt");
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Genre> genreActualList = genreService.getActualListGenres(baseResponse);
        Assert.assertFalse(genreActualList.isEmpty(), "List of Genres is empty");
    }
}
