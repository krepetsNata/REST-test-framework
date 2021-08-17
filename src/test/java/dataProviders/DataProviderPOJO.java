package dataProviders;

import constants.FileNames;
import org.testng.annotations.DataProvider;
import pojo.Author;
import pojo.Book;
import pojo.Genre;
import utils.ParsingAndConvertations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderPOJO {

//    final static String CSV_FILE_AUTHORS = "src/test/java/dataProviders/AuthorTestObject.csv";
//    final static String CSV_FILE_NEW_AUTHOR = "src/test/java/dataProviders/NewAuthorTestObject.csv";
//    final static String CSV_FILE_BOOKS = "src/test/java/dataProviders/BookTestObject.csv";
//    final static String CSV_FILE_NEW_BOOK = "src/test/java/dataProviders/NewBookTestObject.csv";
//    final static String CSV_FILE_GENRES = "src/test/java/dataProviders/GenreTestObject.csv";
//    final static String CSV_FILE_NEW_GENRE = "src/test/java/dataProviders/NewGenreTestObject.csv";

    @DataProvider(name = "dpTestAuthor")
    public Object[][] dpGetListAuthorsFromFile() { //A TestNG DataProvider must return either Object[][] or Iterator<Object[]>
        ParsingAndConvertations parsingAndConvertations = new ParsingAndConvertations();
        List<Author> authorsObjList = parsingAndConvertations.getAuthorsList(FileNames.CSV_FILE_AUTHORS.getFileName());
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
        List<Author> authorsObjList = parsingAndConvertations.getAuthorsList(FileNames.CSV_FILE_NEW_AUTHOR.getFileName());
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
        List<Book> booksObjList = parsingAndConvertations.getBooksList(FileNames.CSV_FILE_BOOKS.getFileName());
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
        List<Book> booksObjList = parsingAndConvertations.getBooksList(FileNames.CSV_FILE_NEW_BOOK.getFileName());
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
        List<Genre> genresObjList = parsingAndConvertations.getGenresList(FileNames.CSV_FILE_GENRES.getFileName());
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
        List<Genre> genresObjList = parsingAndConvertations.getGenresList(FileNames.CSV_FILE_NEW_GENRE.getFileName());
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

        Object a[][] = dpGetNewBookFromFile();
        Object b[][] = dpGetListAuthorsFromFile();
        Object c[][] = dpGetListGenresFromFile();

        int size = dpGetNewBookFromFile().length;
        Object[][] combined = new Object[size][];

        for (int i = 0; i < size; i++) {
            combined[i] = new Object[3];
            combined[i][0] = a[i][i];
            combined[i][1] = b[i][i];
            combined[i][2] = c[i][i];
        }
        return combined;
    }

    @DataProvider(name = "combinedDPOldAuthorOldGenre")
    public Object[][] combinedDataProviderOldAOldG() {

        Object b[][] = dpGetListAuthorsFromFile();
        Object c[][] = dpGetListGenresFromFile();

        int size = b.length;
        Object[][] combined = new Object[size][];

        for (int i = 0; i < size; i++) {
            combined[i] = new Object[2];
            combined[i][0] = b[i][i];
            combined[i][1] = c[i][i];
        }
        return combined;
    }
}
