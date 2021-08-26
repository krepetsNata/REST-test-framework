package dataProviders;

import constants.FileNames;
import org.testng.annotations.DataProvider;
import pojo.Author;
import pojo.Book;
import pojo.Genre;
import utils.ParsingAndConvert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataProviderPOJO {

    @DataProvider(name = "dpTestAuthor")
    public Object[][] dpGetListAuthorsFromFile() { //A TestNG DataProvider must return either Object[][] or Iterator<Object[]>
        ParsingAndConvert parsingAndConvert = new ParsingAndConvert();
        List<Author> authorsObjList = parsingAndConvert.getAuthorsList(FileNames.CSV_FILE_AUTHORS.getFileName());
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
    public Object[][] dpGetNewAuthorFromFile() {
        ParsingAndConvert parsingAndConvert = new ParsingAndConvert();
        List<Author> authorsObjList = parsingAndConvert.getAuthorsList(FileNames.CSV_FILE_NEW_AUTHOR.getFileName());
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
        ParsingAndConvert parsingAndConvert = new ParsingAndConvert();
        List<Book> booksObjList = parsingAndConvert.getBooksList(FileNames.CSV_FILE_BOOKS.getFileName());
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
        ParsingAndConvert parsingAndConvert = new ParsingAndConvert();
        List<Book> booksObjList = parsingAndConvert.getBooksList(FileNames.CSV_FILE_NEW_BOOK.getFileName());
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
        ParsingAndConvert parsingAndConvert = new ParsingAndConvert();
        List<Genre> genresObjList = parsingAndConvert.getGenresList(FileNames.CSV_FILE_GENRES.getFileName());
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
        ParsingAndConvert parsingAndConvert = new ParsingAndConvert();
        List<Genre> genresObjList = parsingAndConvert.getGenresList(FileNames.CSV_FILE_NEW_GENRE.getFileName());
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
        Object newBookFromFile[][] = dpGetNewBookFromFile();
        Object oldAuthorFromFile[][] = dpGetListAuthorsFromFile();
        Object oldGenreFromFile[][] = dpGetListGenresFromFile();

        int size = dpGetNewBookFromFile().length;
        Object[][] combined = new Object[size][];

        for (int i = 0; i < size; i++) {
            combined[i] = new Object[3];
            combined[i][0] = newBookFromFile[i][i];
            combined[i][1] = oldAuthorFromFile[i][i];
            combined[i][2] = oldGenreFromFile[i][i];
        }
        return combined;
    }

    @DataProvider(name = "combinedDPOldAuthorOldGenre")
    public Object[][] combinedDataProviderOldAOldG() {
        Object oldAuthorFromFile[][] = dpGetListAuthorsFromFile();
        Object oldGenreFromFile[][] = dpGetListGenresFromFile();

        int size = oldAuthorFromFile.length;
        Object[][] combined = new Object[size][];

        for (int i = 0; i < size; i++) {
            combined[i] = new Object[2];
            combined[i][0] = oldAuthorFromFile[i][i];
            combined[i][1] = oldGenreFromFile[i][i];
        }
        return combined;
    }

    @DataProvider(name = "searchAuthorsQueries")
    public Object[][] dpSearchAuthorsQueriesFromFile() throws IOException {
        return ParsingAndConvert.readCSVFileForDP(FileNames.CSV_FILE_SEARCH_AUTHORS.getFileName());
    }

    @DataProvider(name = "searchBooksQueries")
    public Object[][] dpSearchBooksQueriesFromFile() throws IOException {
        return ParsingAndConvert.readCSVFileForDP(FileNames.CSV_FILE_SEARCH_BOOKS.getFileName());
    }

    @DataProvider(name = "searchGenresQueries")
    public Object[][] dpSearchGenresQueriesFromFile() throws IOException {
        return ParsingAndConvert.readCSVFileForDP(FileNames.CSV_FILE_SEARCH_GENRES.getFileName());
    }
}
