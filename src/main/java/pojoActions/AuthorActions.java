package pojoActions;

import static io.restassured.RestAssured.*;

import config.ServiceConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.*;

import java.util.ArrayList;
import java.util.List;

public class AuthorActions {

    static Response responseAuthor;
    static List<Author> authors = new ArrayList<>();
    //Author author;

    public static Response addAuthorPost(Author author) {
        RestAssured.baseURI = ServiceConfig.HOST;
        RequestSpecification request = RestAssured.given().header("Content-Type", "application/json");
        Response response = request.body(author).post("/api/library/author");
        return response;
    }

    public static Response updateAuthorPut(Author author) {
        RequestSpecification request = RestAssured.given().header("Content-Type", "application/json");
        Response response = request.body(author).put("/api/library/author");
        return response;
    }

    public static Response getAuthorByAuthorIdGet(int authorId) {
        RequestSpecification request = RestAssured.given().header("Content-Type", "application/json");
        Response response = request.get("/api/library/author/" + authorId);
        return response;
    }

    public static List<Author> getAllAuthorsGet() {
        int pageNum = 1;
        List<Author> partAuthors = null;
        do {
            responseAuthor = given().param("page", pageNum).get("/api/library/authors");
            partAuthors = responseAuthor.jsonPath().getList(".", Author.class);
            if (partAuthors.size() != 0) {
                authors.addAll(partAuthors);
                System.out.println("PAGENUM: " + pageNum);
                pageNum++;
            } else {
                pageNum = 0;
            }
        } while (pageNum != 0);
        return authors;
    }

    public static Response deleteAuthorDelete(int authorId) {
        RequestSpecification request = RestAssured.given().header("Content-Type", "application/json");
        Response response = request.delete("/api/library/author/" + authorId);
        return response;
    }

    public void searchAuthorsByNameAndSurnameGet() {
    }

    public void getAuthorByBookIdGet() {
    }

    public void getAuthorsByGenreId() {
    }

    public static void main(String[] args) {
        Author author = new Author(9999, new AuthorBirth("Mykolaiv", "Ukraine", java.time.LocalDate.now()), "new author", new AuthorName("firstName", "secondName"), "ukrainian");

        Response response = addAuthorPost(author);

//        author.setAuthorDescription("updated author descr");
//        System.out.println(updateAuthorPut(author).asPrettyString());
//
//        System.out.println(getAuthorByAuthorIdGet(author.getAuthorId()).asPrettyString());
//
//        deleteAuthorDelete(author.getAuthorId());
//
//        getAllAuthorsGet().forEach(System.out::println);
//
//        Response responseBook = get("/api/library/books");
//        List<Book> books = responseBook.jsonPath().getList(".", Book.class);
//        books.forEach(System.out::println);
//
//        Response responseGenre = get("/api/library/genres");
//        List<Genre> genres = responseGenre.jsonPath().getList(".", Genre.class);
//        genres.forEach(System.out::println);
    }
}
