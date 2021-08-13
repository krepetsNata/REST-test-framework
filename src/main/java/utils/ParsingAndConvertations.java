package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import pojo.Author;
import pojo.AuthorBirth;
import pojo.AuthorName;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParsingAndConvertations {

    //*********************************************************************************************
    //this methods use for convert csv file to pojo

    final static String CSV_FILE = "src/test/java/dataProviders/AuthorTestObject.csv";

    public List<Author> getAuthorsList() {
        List<Author> authorsList = new ArrayList<>();
        Path pathToFile = Paths.get(CSV_FILE);
        try (BufferedReader buffer = Files.newBufferedReader(pathToFile)) {
            String row = buffer.readLine();
            while (row != null) {
                String[] attrib = row.split(",");
                Author author = getAuthorObj(attrib);
                authorsList.add(author);
                row = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authorsList;
    }

    private Author getAuthorObj(String[] attrib) {
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
    //*******************************************************************************

    public static String getPrettyJsonString(String uglyJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(uglyJson);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
}
