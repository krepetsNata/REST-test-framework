package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.testng.annotations.DataProvider;
import pojo.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParsingAndConvert<T> {

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

    public static Object[][] readCSVFileForDP(String CSV_FILE) throws IOException {
        List<String> stringsList = Files.readAllLines(Paths.get(CSV_FILE));
        Object[][] stringsObj = new Object[stringsList.size()][];

        for (int i = 0; i < stringsList.size(); i++) {
            for (int j = 0; j < 1; j++) {
                stringsObj[i] = new Object[1];
                stringsObj[i][j] = stringsList.get(i);
            }
        }
        return stringsObj;
    }

    //*********************************************************************************************
    //this methods use for convert csv file to pojo
    private List<T> csvParser(String SAMPLE_CSV_FILE_PATH, Class clazz) {
        List<T> list = null;

        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        ) {
            ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
            ms.setType(clazz);

            CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(clazz)
                    .withMappingStrategy(ms)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            list = csvToBean.parse();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Author> getAuthorsList2(String CSV_FILE_AUTHORS) {
//        List<Author> authorsList = (List<Author>) csvParser(CSV_FILE_AUTHORS, Author.class);
//        return authorsList;
        List<Author> authorsList = null;

        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_AUTHORS));
        ) {
            ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();

            ms.setType(AuthorName.class);
            ms.setType(AuthorBirth.class);
            ms.setType(Author.class);

            CsvToBean<Author> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(AuthorName.class)
                    .withType(AuthorBirth.class)
                    .withType(Author.class)
                    .withMappingStrategy(ms)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            authorsList = csvToBean.parse();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return authorsList;
    }


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
        List<Genre> genresList = (List<Genre>) csvParser(CSV_FILE_GENRES, Genre.class);
        return genresList;
    }

    //*******************************************************************************
}
