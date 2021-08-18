import constants.FileNames;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import pojo.Author;
import pojo.Book;
import pojo.Genre;
import service.AuthorService;
import service.BookService;
import service.GenreService;
import utils.ParsingAndConvert;

import java.lang.reflect.Method;

public class BaseTest {
    private final Logger LOG = Logger.getLogger(BaseTest.class);
    private Test test;

    ParsingAndConvert parsingAndConvert = new ParsingAndConvert();

    private BookService bookService = new BookService();
    private AuthorService authorService = new AuthorService();
    private GenreService genreService = new GenreService();

    private Book baseNewBook;
    private Author baseNewAuthor;
    private Genre baseNewGenre;


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        System.out.println("beforeMethod");
        test = method.getAnnotation(Test.class);
        LOG.info(String.format("Test '%s' started.", method.getName()));
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(final Method method) {
        System.out.println("afterMethod");
        LOG.info(String.format("Test '%s' completed.", method.getName()));
    }

    @BeforeGroups(groups = "authorGroup")
    public void beforeGroupAuthor() {
        System.out.println("beforeGroupAuthor");
        baseNewAuthor = parsingAndConvert.getNewAuthorObj(FileNames.CSV_FILE_NEW_AUTHOR.getFileName());
    }

    @AfterGroups(groups = "authorGroup")
    public void afterGroupAuthor() {
        System.out.println("afterGroupAuthor");
        baseNewAuthor = null;
    }

    @BeforeGroups(groups = "bookGroup")
    public void beforeGroupBook() {
        System.out.println("beforeGroupBook");
        baseNewBook = parsingAndConvert.getNewBookObj(FileNames.CSV_FILE_NEW_BOOK.getFileName());
        baseNewAuthor = parsingAndConvert.getNewAuthorObj(FileNames.CSV_FILE_AUTHORS.getFileName());//existed author
        baseNewGenre = parsingAndConvert.getNewGenreObj(FileNames.CSV_FILE_GENRES.getFileName());//existed genre
    }

    @AfterGroups(groups = "bookGroup")
    public void afterGroupBook() {
        System.out.println("afterGroupBook");
        baseNewBook = null;
        baseNewAuthor = null;
        baseNewGenre = null;
    }

    @BeforeGroups(groups = "genreGroup")
    public void beforeGroupGenre() {
        System.out.println("beforeGroupGenre");
        baseNewGenre = parsingAndConvert.getNewGenreObj(FileNames.CSV_FILE_NEW_GENRE.getFileName());
    }

    @AfterGroups(groups = "genreGroup")
    public void afterGroupGenre() {
        System.out.println("afterGroupGenre");
        baseNewGenre = null;
    }

    @BeforeMethod(groups = "bookGroup")
    public void beforeMethodBook(Method method) {
        System.out.println("beforeMethodBook");
        if (method.getName().contains("verifyDeleting") || method.getName().contains("verifyUpdating")) {
            bookService.addBookPost(baseNewBook, baseNewAuthor.getAuthorId(), baseNewGenre.getGenreId());
        }
    }

    @AfterMethod(groups = "bookGroup")
    public void afterMethodBook(Method method) {
        System.out.println("afterMethodBook");
        if (method.getName().contains("verifyUpdating") || method.getName().contains("verifyCreating")) {
            bookService.deleteBookDelete(baseNewBook.getBookId());
        }
    }

    @BeforeMethod(groups = "authorGroup")
    public void beforeMethodAuthor(Method method) {
        System.out.println("beforeMethodAuthor");
        if (method.getName().contains("verifyDeleting") || method.getName().contains("verifyUpdating")) {
            authorService.addAuthorPost(baseNewAuthor);
        }
    }

    @AfterMethod(groups = "authorGroup")
    public void afterMethodAuthor(Method method) {
        System.out.println("afterMethodAuthor");
        if (method.getName().contains("verifyUpdating") || method.getName().contains("verifyCreating")) {
            authorService.deleteAuthorDelete(baseNewAuthor.getAuthorId());
        }
    }

    @BeforeMethod(groups = "genreGroup")
    public void beforeMethodGenre(Method method) {
        System.out.println("beforeMethodGenre");
        if (method.getName().contains("verifyDeleting") || method.getName().contains("verifyUpdating")) {
            genreService.addGenrePost(baseNewGenre);
        }
    }

    @AfterMethod(groups = "genreGroup")
    public void afterMethodGenre(Method method) {
        System.out.println("afterMethodGenre");
        if (method.getName().contains("verifyUpdating") || method.getName().contains("verifyCreating")) {
            genreService.deleteGenreDelete(baseNewGenre.getGenreId());
        }
    }
}