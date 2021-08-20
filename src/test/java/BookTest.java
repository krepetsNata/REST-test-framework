import dataProviders.DataProviderPOJO;
import entity.ListOptions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import pojo.Author;
import pojo.Book;
import pojo.Genre;
import response.BaseResponse;
import service.BookService;
import utils.Validator;

import java.util.List;

import static org.apache.http.HttpStatus.*;

@Feature("Book api tests")
public class BookTest extends BaseTest {

    BookService bookService = new BookService();

    @Test(description = "Verifying creating new Book object by Author id and Genre idwith POST method",
            dataProvider = "combinedDPNewBookOldAuthorOldGenre", dataProviderClass = DataProviderPOJO.class,
            groups = "bookGroup")
    public void verifyCreatingBookPostRequest(Book book, Author author, Genre genre) {
        BaseResponse baseResponse = bookService.addBookPost(book, author.getAuthorId(), genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_CREATED);
        Validator.validateSameObjects(baseResponse, book);
    }

    @Test(description = "Verifying updating Book object with PUT method",
            dataProvider = "dpNewBook", dataProviderClass = DataProviderPOJO.class,
            groups = "bookGroup")
    public void verifyUpdatingBookPutRequest(Book book) {
        book.setBookDescription("updated book description");
        BaseResponse<Book> baseResponse = bookService.updateBookPut(book);
        Validator.validateStatusCode(baseResponse, SC_OK);
        Validator.validateSameObjects(baseResponse, book);
    }

    @Test(description = "Verifying getting 1 Book object by id with GET method",
            dataProvider = "dpTestBook", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetBookByIdGetRequest(Book book) {
        BaseResponse<Book> baseResponse = bookService.getBookByBookIdGet(book.getBookId());
        Validator.validateStatusCode(baseResponse, SC_OK);
    }

    @Test(description = "Verifying getting all Book objects with GET method",
            groups = "withoutPreconditionGroup")
    public void verifyGetAllBooksGetRequest() {
        BaseResponse<Book> baseResponse = bookService.getBooks(new ListOptions());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Book> bookActualList = bookService.getActualListBooks(baseResponse);
        Validator.validateListIsEmpty(bookActualList, false);
    }

    @Test(description = "Verifying deleting 1 Book object by id with DELETE method",
            dataProvider = "dpNewBook", dataProviderClass = DataProviderPOJO.class,
            groups = "bookGroup")
    public void verifyDeletingBookByIdDeleteRequest(Book book) {
        BaseResponse<Book> baseResponse = bookService.deleteBookDelete(book.getBookId());
        Validator.validateStatusCode(baseResponse, SC_NO_CONTENT);
        baseResponse = bookService.getBookByBookIdGet(book.getBookId());
        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);
        Book bookActual = bookService.getActualObjBook(baseResponse);
        Validator.validateObjectIsNull(bookActual, true);
    }

    @Test(description = "Negative case when element not found - verifying getting 1 Book object by id with GET method",
            dataProvider = "dpNewBook", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetNotFoundBookByIdGetRequestNegativeCase(Book book) {
        BaseResponse<Book> baseResponse = bookService.getBookByBookIdGet(book.getBookId());
        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);
        Book bookActual = bookService.getActualObjBook(baseResponse);
        Validator.validateObjectIsNull(bookActual, true);
    }

    @Test(description = "Verifying getting List of Books object by Author id with GET method",
            dataProvider = "dpTestAuthor", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetBooksByAuthorIdGetRequest(Author author) {
        BaseResponse<Book> baseResponse = bookService.getBooksByAuthorIdGet(new ListOptions(), author.getAuthorId());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Book> bookActualList = bookService.getActualListBooks(baseResponse);
        Validator.validateListIsEmpty(bookActualList, false);
    }

    @Test(description = "Verifying getting List of Books object by Genre id with GET method",
            dataProvider = "dpTestGenre", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetBooksByGenreIdGetRequest(Genre genre) {
        BaseResponse<Book> baseResponse = bookService.getBooksByGenreIdGet(new ListOptions(), genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Book> bookActualList = bookService.getActualListBooks(baseResponse);
        Validator.validateListIsEmpty(bookActualList, false);
    }

    @Test(description = "Verifying getting List of Books object by Author id and Genre id with GET method",
            dataProvider = "combinedDPOldAuthorOldGenre", dataProviderClass = DataProviderPOJO.class,
            groups = "withoutPreconditionGroup")
    public void verifyGetBooksByAuthorIdGenreIdGetRequest(Author author, Genre genre) {
        BaseResponse<Book> baseResponse = bookService.getBooksByAuthorIdGenreIdGet(new ListOptions(), author.getAuthorId(), genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Book> bookActualList = bookService.getActualListBooks(baseResponse);
        Validator.validateListIsEmpty(bookActualList, false);
    }

    @Test(description = "Verifying searching books by its name",
            groups = "withoutPreconditionGroup")
    @Description("Verify searching books by its name")
    public void verifySearchBooksGetRequest() {//add DP for searching
        BaseResponse<Book> baseResponse = bookService.getBooksSearchGet(new ListOptions(), "Dolor");
        System.out.println("==>>"+baseResponse.getResponse().asString());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Book> bookActualList = bookService.getActualListBooks(baseResponse);
        Validator.validateListIsEmpty(bookActualList, false);
    }
}
