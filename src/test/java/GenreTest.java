import dataProviders.DataProviderPOJO;
import entity.ListOptions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import pojo.Author;
import pojo.Book;
import pojo.Genre;
import response.BaseResponse;
import service.GenreService;
import utils.Validator;

import java.util.List;

import static org.apache.http.HttpStatus.*;

@Feature("Genre api tests")
public class GenreTest extends BaseTest {

    GenreService genreService = new GenreService();

    @Test(description = "Verifying creating new Genre object with POST method",
            dataProvider = "dpNewGenre", dataProviderClass = DataProviderPOJO.class,
            groups = "genreGroup")
    public void verifyCreatingGenrePostRequest(Genre genre) {
        BaseResponse<Genre> baseResponse = genreService.addGenrePost(genre);
        Validator.validateStatusCode(baseResponse, SC_CREATED);
        Validator.validateSameObjects(baseResponse, genre);
    }

    @Test(description = "Verifying updating Genre object with PUT method",
            dataProvider = "dpNewGenre", dataProviderClass = DataProviderPOJO.class,
            groups = "genreGroup")
    public void verifyUpdatingGenrePutRequest(Genre genre) {
        genre.setGenreDescription("updated genre description");
        BaseResponse<Genre> baseResponse = genreService.updateGenrePut(genre);
        Validator.validateStatusCode(baseResponse, SC_OK);
        Validator.validateSameObjects(baseResponse, genre);
    }

    @Test(description = "Verifying getting 1 Genre object by id with GET method",
            dataProvider = "dpTestGenre", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetGenreByIdGetRequest(Genre genre) {
        BaseResponse<Genre> baseResponse = genreService.getGenreByGenreIdGet(genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_OK);
    }

    @Test(description = "Verifying getting all Genre objects with GET method",
            groups = "withoutPreconditionGroup")
    public void verifyGetAllGenresGetRequest() {
        BaseResponse<Genre> baseResponse = genreService.getGenres(new ListOptions());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Genre> genresActualList = genreService.getActualListGenres(baseResponse);
        Validator.validateListIsEmpty(genresActualList, false);
    }

    @Test(description = "Verifying deleting 1 Genre object by id with DELETE method",
            dataProvider = "dpNewGenre", dataProviderClass = DataProviderPOJO.class,
            groups = "genreGroup")
    public void verifyDeletingGenreByIdDeleteRequest(Genre genre) {
        BaseResponse<Genre> baseResponse = genreService.deleteGenreDelete(genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_NO_CONTENT);
        baseResponse = genreService.getGenreByGenreIdGet(genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);
        Genre genreActual = genreService.getActualObjGenre(baseResponse);
        Validator.validateObjectIsNull(genreActual, true);
    }

    @Test(description = "Negative case when element not found - verifying getting 1 Genre object by id with GET method",
            dataProvider = "dpNewGenre", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetNotFoundGenreByIdGetRequestNegativeCase(Genre genre) {
        BaseResponse<Genre> baseResponse = genreService.getGenreByGenreIdGet(genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);
        Genre genreActual = genreService.getActualObjGenre(baseResponse);
        Validator.validateObjectIsNull(genreActual, true);
    }

    @Test(description = "Verifying getting all Genre objects by AuthorId with GET method",
            dataProvider = "dpTestAuthor", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetAllGenresByAuthorIdGetRequest(Author author) {
        BaseResponse<Genre> baseResponse = genreService.getGenresByAuthorIdGet(new ListOptions(),author.getAuthorId());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Genre> genresActualList = genreService.getActualListGenres(baseResponse);
        Validator.validateListIsEmpty(genresActualList, false);
    }

    @Test(description = "Verifying getting all Genre objects by BookId with GET method",
            dataProvider = "dpTestBook", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetGenreByBookIdGetRequest(Book book) {
        BaseResponse<Genre> baseResponse = genreService.getGenreByBookIdGet(new ListOptions(), book.getBookId());
        Validator.validateStatusCode(baseResponse, SC_OK);
        Genre genreActual = genreService.getActualObjGenre(baseResponse);
        Validator.validateObjectIsNull(genreActual, false);
    }

    @Test(description = "Verifying searching Genres by its name",
            groups = "withoutPreconditionGroup")
    @Description("Verify searching Genres by its name")
    public void verifySearchGenresGetRequest() {//add DP for serching
        BaseResponse<Genre> baseResponse = genreService.getGenresSearchGet(new ListOptions(), "Myt");
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Genre> genresActualList = genreService.getActualListGenres(baseResponse);
        Validator.validateListIsEmpty(genresActualList, false);
    }
}
