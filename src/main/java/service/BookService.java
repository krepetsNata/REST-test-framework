package service;

import client.HttpClient;
import config.ServiceConfig;
import entity.ListOptions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Author;
import pojo.Book;
import response.BaseResponse;
import utils.EndpointBuilder;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BookService {


    static Response responseBook;
    static List<Book> books = new ArrayList<>();


    /**
     * Method returns actual Book object from response
     *
     * @param baseResponse wrapper for Response which we got
     * @return actual Book object from response
     */
    public static Book getActualBook(BaseResponse baseResponse) {
        Response response = baseResponse.getResponse();
        Book actualObj = response.jsonPath().getObject(".", Book.class);
        return actualObj;
    }
}
