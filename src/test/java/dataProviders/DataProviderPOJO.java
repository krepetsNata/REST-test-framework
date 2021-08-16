package dataProviders;

import org.testng.annotations.DataProvider;
import pojo.Author;
import pojo.Book;
import pojo.Genre;
import utils.ParsingAndConvertations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DataProviderPOJO {

    final static String CSV_FILE_AUTHORS = "src/test/java/dataProviders/AuthorTestObject.csv";
    final static String CSV_FILE_NEW_AUTHOR = "src/test/java/dataProviders/NewAuthorTestObject.csv";
    final static String CSV_FILE_BOOKS = "src/test/java/dataProviders/BookTestObject.csv";
    final static String CSV_FILE_NEW_BOOK = "src/test/java/dataProviders/NewBookTestObject.csv";
    final static String CSV_FILE_GENRES = "src/test/java/dataProviders/GenreTestObject.csv";
    final static String CSV_FILE_NEW_GENRE = "src/test/java/dataProviders/NewGenreTestObject.csv";

    @DataProvider(name = "dpTestAuthor")
    public Object[][] dpGetListAuthorsFromFile() { //A TestNG DataProvider must return either Object[][] or Iterator<Object[]>
        ParsingAndConvertations parsingAndConvertations = new ParsingAndConvertations();
        List<Author> authorsObjList = parsingAndConvertations.getAuthorsList(CSV_FILE_AUTHORS);
        // authorsObjList.forEach(System.out::println);
        Object[][] authorObj = new Object[authorsObjList.size()][];

        for (int i = 0; i < authorsObjList.size(); i++) {
            for (int j = 0; j < 1; j++) {
                authorObj[i] = new Object[1];
                authorObj[i][j] = authorsObjList.get(i);
            }
        }
        return authorObj;
    }

    @DataProvider(name = "dpNewAuthor")
    public Object[][] dpGetNewAuthorFromFile() { //A TestNG DataProvider must return either Object[][] or Iterator<Object[]>
        ParsingAndConvertations parsingAndConvertations = new ParsingAndConvertations();
        List<Author> authorsObjList = parsingAndConvertations.getAuthorsList(CSV_FILE_NEW_AUTHOR);
        // authorsObjList.forEach(System.out::println);
        Object[][] authorObj = new Object[authorsObjList.size()][];

        for (int i = 0; i < authorsObjList.size(); i++) {
            for (int j = 0; j < 1; j++) {
                authorObj[i] = new Object[1];
                authorObj[i][j] = authorsObjList.get(i);
            }
        }
        return authorObj;
    }

    @DataProvider(name = "dpTestBook")
    public Object[][] dpGetListBooksFromFile() {
        ParsingAndConvertations parsingAndConvertations = new ParsingAndConvertations();
        List<Book> booksObjList = parsingAndConvertations.getBooksList(CSV_FILE_BOOKS);
        // authorsObjList.forEach(System.out::println);
        Object[][] bookObj = new Object[booksObjList.size()][];

        for (int i = 0; i < booksObjList.size(); i++) {
            for (int j = 0; j < 1; j++) {
                bookObj[i] = new Object[1];
                bookObj[i][j] = booksObjList.get(i);
            }
        }
        return bookObj;
    }

    @DataProvider(name = "dpNewBook")
    public Object[][] dpGetNewBookFromFile() {
        ParsingAndConvertations parsingAndConvertations = new ParsingAndConvertations();
        List<Book> booksObjList = parsingAndConvertations.getBooksList(CSV_FILE_NEW_BOOK);
        // authorsObjList.forEach(System.out::println);
        Object[][] bookObj = new Object[booksObjList.size()][];

        for (int i = 0; i < booksObjList.size(); i++) {
            for (int j = 0; j < 1; j++) {
                bookObj[i] = new Object[1];
                bookObj[i][j] = booksObjList.get(i);
            }
        }
        return bookObj;
    }

    @DataProvider(name = "dpTestGenre")
    public Object[][] dpGetListGenresFromFile() {
        ParsingAndConvertations parsingAndConvertations = new ParsingAndConvertations();
        List<Genre> genresObjList = parsingAndConvertations.getGenresList(CSV_FILE_GENRES);
        // authorsObjList.forEach(System.out::println);
        Object[][] genreObj = new Object[genresObjList.size()][];

        for (int i = 0; i < genresObjList.size(); i++) {
            for (int j = 0; j < 1; j++) {
                genreObj[i] = new Object[1];
                genreObj[i][j] = genresObjList.get(i);
            }
        }
        return genreObj;
    }

    @DataProvider(name = "dpNewGenre")
    public Object[][] dpGetNewGenreFromFile() {
        ParsingAndConvertations parsingAndConvertations = new ParsingAndConvertations();
        List<Genre> genresObjList = parsingAndConvertations.getGenresList(CSV_FILE_NEW_GENRE);
        // authorsObjList.forEach(System.out::println);
        Object[][] genreObj = new Object[genresObjList.size()][];

        for (int i = 0; i < genresObjList.size(); i++) {
            for (int j = 0; j < 1; j++) {
                genreObj[i] = new Object[1];
                genreObj[i][j] = genresObjList.get(i);
            }
        }
        return genreObj;
    }

    @DataProvider(name = "combinedDPNewBookOldAuthorOldGenre")
    public Object[][] combinedDataProvider() {
        return Stream.of(dpGetNewBookFromFile(), dpGetListAuthorsFromFile(), dpGetListGenresFromFile())
                .flatMap(Arrays::stream)
                .toArray(Object[][]::new);
    }
}
