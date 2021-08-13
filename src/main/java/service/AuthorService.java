package service;

import client.HttpClient;
import config.ServiceConfig;
import entity.ListOptions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Author;
import response.BaseResponse;
import utils.EndpointBuilder;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AuthorService {


    static Response responseAuthor;
    static List<Author> authors = new ArrayList<>();

    public BaseResponse addAuthorPost(Author author) {
        String endpoint = new EndpointBuilder().pathParameter("author").get();
        return HttpClient.post(endpoint, author.toString());
    }


    public BaseResponse updateAuthorPut(Author author) {
        String endpoint = new EndpointBuilder().pathParameter("author").get();
        return HttpClient.put(endpoint, author.toString());
    }

    public BaseResponse getAuthorByAuthorIdGet(int authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return HttpClient.get(endpoint);
    }

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

    public BaseResponse getAuthors(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("authors");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return HttpClient.get(endpoint.get());
    }

    public BaseResponse deleteAuthorDelete(int authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return HttpClient.delete(endpoint);
    }

    /**
     * Method returns actual Author object from response
     *
     * @param baseResponse wrapper for Response which we got
     * @return actual Author object from response
     */
    public static Author getActualAuthor(BaseResponse baseResponse) {
        Response response = baseResponse.getResponse();
        Author actualObj = response.jsonPath().getObject(".", Author.class);
        return actualObj;
    }
}
