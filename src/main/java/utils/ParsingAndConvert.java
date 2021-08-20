package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import pojo.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParsingAndConvert {

    public Book getNewBookObj(String CSV_FILE_NEW_BOOK) {
        Book bookObj = getBooksList(CSV_FILE_NEW_BOOK).get(0);
        return bookObj;
    }

    public Genre getNewGenreObj(String CSV_FILE_NEW_GENRE) {
        Genre genreObj = getGenresList(CSV_FILE_NEW_GENRE).get(0);
        return genreObj;
    }

    public Author getNewAuthorObj(String CSV_FILE_NEW_AUTHOR) {
        Author authorObj = getAuthorsList(CSV_FILE_NEW_AUTHOR).get(0);
        return authorObj;
    }

    public static String getPrettyJsonString(String uglyJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(uglyJson);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }


    //*********************************************************************************************
    //this methods use for convert csv file to pojo

    public List<Author> getAuthorsList(String CSV_FILE_AUTHORS) {
        List<Author> authorsList = new ArrayList<>();
        Path pathToFile = Paths.get(CSV_FILE_AUTHORS);
        try (BufferedReader buffer = Files.newBufferedReader(pathToFile)) {
            String row = buffer.readLine();
            while (row != null) {
                String[] attrib = row.split(",");
                Author author = setAuthorObj(attrib);
                authorsList.add(author);
                row = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authorsList;
    }

    private Author setAuthorObj(String[] attrib) {
        Author author = new Author();
        AuthorName authorName = new AuthorName();
        AuthorBirth authorBirth = new AuthorBirth();


        authorName.setFirst(attrib[2].trim())
                .setSecond(attrib[3].trim());
        authorBirth.setCity(attrib[4].trim())
                .setCountry(attrib[5].trim())
                .setDate(attrib[6].trim());

        author.setAuthorDescription(attrib[0].trim())
                .setAuthorId(Integer.parseInt(attrib[1].trim()))
                .setAuthorName(authorName)
                .setBirth(authorBirth)
                .setNationality(attrib[7].trim());

        return author;
    }

    public List<Book> getBooksList(String CSV_FILE_BOOKS) {
        List<Book> booksList = new ArrayList<>();
        Path pathToFile = Paths.get(CSV_FILE_BOOKS);
        try (BufferedReader buffer = Files.newBufferedReader(pathToFile)) {
            String row = buffer.readLine();
            while (row != null) {
                String[] attrib = row.split(",");
                Book book = setBookObj(attrib);
                booksList.add(book);
                row = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return booksList;
    }

    private Book setBookObj(String[] attrib) {
        Book book = new Book();
        BookAdditional bookAdditional = new BookAdditional();
        BookAdditionalSize bookAdditionalSize = new BookAdditionalSize();

        bookAdditionalSize.setHeight(Float.parseFloat(attrib[1].trim()))
                .setWidth(Float.parseFloat(attrib[3].trim()))
                .setLength(Float.parseFloat(attrib[2].trim()));

        bookAdditional.setPageCount(Integer.parseInt(attrib[0].trim()))
                .setSize(bookAdditionalSize);

        book.setAdditional(bookAdditional)
                .setBookDescription(attrib[4].trim())
                .setBookId(Integer.parseInt(attrib[5].trim()))
                .setBookLanguage(attrib[6].trim())
                .setBookName(attrib[7].trim())
                .setPublicationYear(Integer.parseInt(attrib[8].trim()));

        return book;
    }

    public List<Genre> getGenresList(String CSV_FILE_GENRES) {
        List<Genre> genresList = new ArrayList<>();
        Path pathToFile = Paths.get(CSV_FILE_GENRES);
        try (BufferedReader buffer = Files.newBufferedReader(pathToFile)) {
            String row = buffer.readLine();
            while (row != null) {
                String[] attrib = row.split(",");
                Genre genre = setGenreObj(attrib);
                genresList.add(genre);
                row = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return genresList;
    }

    private Genre setGenreObj(String[] attrib) {
        Genre genre = new Genre();

        genre.setGenreDescription(attrib[0].trim())
                .setGenreId(Integer.parseInt(attrib[1].trim()))
                .setGenreName(attrib[2].trim());

        return genre;
    }

    //*******************************************************************************
}
