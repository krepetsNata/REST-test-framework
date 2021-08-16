import dataProviders.DataProviderPOJO;
import entity.ListOptions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
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
            dataProvider = "dpNewBook", dataProviderClass = DataProviderPOJO.class)
    public void verifyCreatingBookPostRequest(Book book, Author author, Genre genre) {
        author.setAuthorId(37);
        genre.setGenreId(644);
        BaseResponse baseResponse = bookService.addBookPost(book, author.getAuthorId(), genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_CREATED);
        Validator.validateSameObjects(baseResponse, book);
        bookService.deleteBookDelete(book.getBookId());
    }

    @Test(description = "Verifying updating Book object with PUT method",
            dataProvider = "dpNewBook", dataProviderClass = DataProviderPOJO.class)
    public void verifyUpdatingBookPutRequest(Book book) {
        bookService.addBookPost(book, 37, 644);
        book.setBookDescription("updated book description");
        BaseResponse<Book> baseResponse = bookService.updateBookPut(book);
        Validator.validateStatusCode(baseResponse, SC_OK);
        Validator.validateSameObjects(baseResponse, book);
        bookService.deleteBookDelete(book.getBookId());
    }

    @Test(description = "Verifying getting 1 Book object by id with GET method",
            dataProvider = "dpTestBook", dataProviderClass = DataProviderPOJO.class)
    public void verifyGetBookByIdGetRequest(Book book) {
        BaseResponse<Book> baseResponse = bookService.getBookByBookIdGet(book.getBookId());
        Validator.validateStatusCode(baseResponse, SC_OK);
    }

    @Test(description = "Verifying getting all Book objects with GET method")
    public void verifyGetAllBooksGetRequest() {
        BaseResponse<Book> baseResponse = bookService.getBooks(new ListOptions());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Book> bookActualList = bookService.getActualListBooks(baseResponse);
        Assert.assertFalse(bookActualList.isEmpty(), "List of books is empty");
    }

    @Test(description = "Verifying deleting 1 Book object by id with DELETE method",
            dataProvider = "dpNewBook", dataProviderClass = DataProviderPOJO.class)
    public void verifyDeletingBookByIdDeleteRequest(Book book) {
        bookService.addBookPost(book, 37, 644);
        BaseResponse<Book> baseResponse = bookService.deleteBookDelete(book.getBookId());
        Validator.validateStatusCode(baseResponse, SC_NO_CONTENT);
        baseResponse = bookService.getBookByBookIdGet(book.getBookId());
        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);
        Book bookActual = bookService.getActualObjBook(baseResponse);
        Assert.assertEquals(bookActual.getBookId(), 0, "object exists");
    }

    @Test(description = "Negative case when element not found - verifying getting 1 Book object by id with GET method",
            dataProvider = "dpNewBook", dataProviderClass = DataProviderPOJO.class)
    public void verifyGetNotFoundBookByIdGetRequestNegativeCase(Book book) {
        BaseResponse<Book> baseResponse = bookService.getBookByBookIdGet(book.getBookId());
        Validator.validateStatusCode(baseResponse, SC_NOT_FOUND);
        Book bookActual = bookService.getActualObjBook(baseResponse);
        Assert.assertEquals(bookActual.getBookId(), 0, "object exists");
    }

    @Test(description = "Verifying getting List of Books object by Author id with GET method",
            dataProvider = "dpTestAuthor", dataProviderClass = DataProviderPOJO.class)
    public void verifyGetBooksByAuthorIdGetRequest(Author author) {
        BaseResponse<Book> baseResponse = bookService.getBooksByAuthorIdGet(new ListOptions(), author.getAuthorId());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Book> bookActualList = bookService.getActualListBooks(baseResponse);
        Assert.assertFalse(bookActualList.isEmpty(), "List of books is empty");
    }

    @Test(description = "Verifying getting List of Books object by Genre id with GET method",
            dataProvider = "dpTestGenre", dataProviderClass = DataProviderPOJO.class)
    public void verifyGetBooksByGenreIdGetRequest(Genre genre) {
        BaseResponse<Book> baseResponse = bookService.getBooksByGenreIdGet(new ListOptions(), genre.getGenreId());
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Book> bookActualList = bookService.getActualListBooks(baseResponse);
        Assert.assertFalse(bookActualList.isEmpty(), "List of books is empty");
    }

    @Test(description = "Verifying getting List of Books object by Author id and Genre id with GET method")
    public void verifyGetBooksByAuthorIdGenreIdGetRequest() {
        BaseResponse<Book> baseResponse = bookService.getBooksByAuthorIdGenreIdGet(new ListOptions(), 37, 644);
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Book> bookActualList = bookService.getActualListBooks(baseResponse);
        Assert.assertFalse(bookActualList.isEmpty(), "List of books is empty");
    }

    @Test(description = "Verifying searching books by its name")
    @Description("Verify searching books by its name")
    public void verifySearchBooksGetRequest() {//add DP for searching
        BaseResponse<Book> baseResponse = bookService.getBooksSearchGet(new ListOptions(), "Dolor");
        Validator.validateStatusCode(baseResponse, SC_OK);
        List<Book> bookActualList = bookService.getActualListBooks(baseResponse);
        Assert.assertFalse(bookActualList.isEmpty(), "List of books is empty");
    }
}
