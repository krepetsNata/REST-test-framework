package service;

import client.HttpClient;
import entity.ListOptions;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import pojo.Author;
import response.BaseResponse;
import utils.EndpointBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class AuthorService {

    private static final Logger LOG = Logger.getLogger(AuthorService.class);

    static Response responseAuthor;
    static List<Author> authors = new ArrayList<>();

    ///api/library/author
    //create new Author
    @Step("Create new Author.")
    public BaseResponse<Author> addAuthorPost(Author author) {
        String endpoint = new EndpointBuilder().pathParameter("author").get();
        return new BaseResponse<>(HttpClient.post(endpoint, author.toString()), Author.class);
    }

    ///api/library/author
    //update existed Author
    @Step("Update existed Author.")
    public BaseResponse<Author> updateAuthorPut(Author author) {
        String endpoint = new EndpointBuilder().pathParameter("author").get();
        return new BaseResponse<>(HttpClient.put(endpoint, author.toString()), Author.class);
    }

    ///api/library/author/{authorId}
    //get Author object by 'authorId'
    @Step("Get Author object by 'authorId'.")
    public BaseResponse<Author> getAuthorByAuthorIdGet(int authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return new BaseResponse<>(HttpClient.get(endpoint), Author.class);
    }

    ///api/library/authors
    //get all Authors
    @Step("Get all Authors.")
    public List<Author> getAllAuthorsGet() {
        int pageNum = 1;
        List<Author> partAuthors = null;
        do {
            responseAuthor = given().param("page", pageNum).get("authors");
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

    ///api/library/authors
    //get all Authors
    @Step("Get all Authors.")
    public BaseResponse<Author> getAuthors(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("authors");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Author.class);
    }

    ///api/library/author/{authorId}
    //delete existed Author
    @Step("Delete existed Author.")
    public BaseResponse<Author> deleteAuthorDelete(int authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return new BaseResponse<>(HttpClient.delete(endpoint), Author.class);
    }

    ///api/library/book/{bookId}/author
    //get Author of special Book
    @Step("Get Author of special Book.")
    public BaseResponse<Author> getAuthorByBookIdGet(ListOptions options, int bookId) {
        String endpoint = new EndpointBuilder().pathParameter("book").pathParameter(bookId).pathParameter("author").get();
        return new BaseResponse<>(HttpClient.get(endpoint), Author.class);
    }

    ///api/library/genre/{genreId}/authors
    //get all Authors in special Genre
    @Step("Get all Authors in special Genre.")
    public BaseResponse<Author> getAuthorsByGenreIdGet(ListOptions options, int genreId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter("genre")
                .pathParameter(genreId)
                .pathParameter("authors")
                .addListOptions(options);
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Author.class);
    }

    ///api/library/authors/search
    //search for author by it name and surname(more than or is 3 symbols)
    @Step("Search for authors by his/her name and surname.")
    public BaseResponse<Author> getAuthorsSearchGet(ListOptions options, String queryWord) {
        HashMap<String, String> queryParams = new HashMap<>();

        if (!queryWord.trim().isEmpty() && queryWord.length() >= 3) {
            queryParams.put("query", queryWord);
        } else {
            LOG.error(queryWord, new Exception("queryParam is wrong - it should be more than 3 symbols and not empty!"));
        }

        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter("authors")
                .pathParameter("search")
                .queryParam("query", queryWord)
                .addListOptions(options);

        return new BaseResponse(HttpClient.get(endpoint.get(), queryParams), Author.class);
    }

    /**
     * Method returns actual Author object from response
     *
     * @param baseResponse wrapper for Response which we got
     * @return actual Author object from response
     */
    @Step("Return actual Author object from response.")
    public Author getActualObjAuthor(BaseResponse baseResponse) {
        Response response = baseResponse.getResponse();
        Author actualObj = null;
        if (response.getStatusCode() != SC_NOT_FOUND)
            actualObj = response.jsonPath().getObject(".", Author.class);
        return actualObj;
    }

    @Step("Return actual list of Authors from response.")
    public List<Author> getActualListAuthors(BaseResponse baseResponse) {
        Response response = baseResponse.getResponse();
        List<Author> actualList = response.jsonPath().getList(".", Author.class);
        return actualList;
    }

    @Step("Return bool type - true if word contains in all items of list and false if this is not.")
    public boolean isContainedQueryWordInAuthorsList(List<Author> authorsList, String queryWord) {
        boolean isContains = false;
        for (Author author : authorsList) {
            isContains = author.getAuthorName().getFirst().contains(queryWord) || author.getAuthorName().getSecond().contains(queryWord);
            if (!isContains) break;
        }
        return isContains;
    }
}
