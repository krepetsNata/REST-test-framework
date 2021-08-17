package service;

import client.HttpClient;
import entity.ListOptions;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.Author;
import pojo.Book;
import response.BaseResponse;
import utils.EndpointBuilder;

import java.util.List;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class BookService {


    ///api/library/book/{authorId}/{genreId}
    //create new Book
    @Step("Create new Book.")
    public BaseResponse<Book> addBookPost(Book book, int authorId, int genreId) {
        String endpoint = new EndpointBuilder().pathParameter("book").pathParameter(authorId).pathParameter(genreId).get();
        return new BaseResponse<>(HttpClient.post(endpoint, book.toString()), Book.class);
    }

    ///api/library/book
    //update existed Book
    @Step("Update existed Book.")
    public BaseResponse<Book> updateBookPut(Book book) {
        String endpoint = new EndpointBuilder().pathParameter("book").get();
        return new BaseResponse<>(HttpClient.put(endpoint, book.toString()), Book.class);
    }

    ///api/library/book/{bookId}
    //get Book object by 'bookId'
    @Step("Get Book object by 'bookId'.")
    public BaseResponse<Book> getBookByBookIdGet(int bookId) {
        String endpoint = new EndpointBuilder().pathParameter("book").pathParameter(bookId).get();
        return new BaseResponse<>(HttpClient.get(endpoint), Book.class);
    }

    ///api/library/books
    //get all Books
    @Step("Get all Books.")
    public BaseResponse<Book> getBooks(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("books");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Book.class);
    }

    ///api/library/book/{bookId}
    //delete existed Book
    @Step("Delete existed Book.")
    public BaseResponse<Book> deleteBookDelete(int bookId) {
        String endpoint = new EndpointBuilder().pathParameter("book").pathParameter(bookId).get();
        return new BaseResponse<>(HttpClient.delete(endpoint), Book.class);
    }

    /**
     * Method returns actual Book object from response
     *
     * @param baseResponse wrapper for Response which we got
     * @return actual Book object from response
     */
    @Step("Return actual Book object from response.")
    public static Book getActualObjBook(BaseResponse baseResponse) {
        Response response = baseResponse.getResponse();
        Book actualObj = null;
        if(response.getStatusCode() != SC_NOT_FOUND)
            actualObj = response.jsonPath().getObject(".", Book.class);
        return actualObj;
    }

    @Step("Return actual Book List from response.")
    public List<Book> getActualListBooks(BaseResponse baseResponse) {
        Response response = baseResponse.getResponse();
        List<Book> actualList = null;
        if(response.getStatusCode() != SC_NOT_FOUND)
            actualList = response.jsonPath().getList(".", Book.class);
        return actualList;
    }

    public Book[] getActualArrayBooks(BaseResponse baseResponse) {
        Response response = baseResponse.getResponse();
        Book[] actualArr = (Book[]) response.jsonPath().getList("$", Book.class).toArray();
        return actualArr;
    }

    ///api/library/author/{authorId}/books
    //get all Books of special Author
    @Step("Get all Books of special Author.")
    public BaseResponse<Book> getBooksByAuthorIdGet(ListOptions options, int authorId) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).pathParameter("books");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Book.class);
    }

    ///api/library/author/{authorId}/genre/{genreId}/books
    //get all Books of special Author in special Genre
    @Step("Get all Books of special Author in special Genre.")
    public BaseResponse<Book> getBooksByAuthorIdGenreIdGet(ListOptions options, int authorId, int genreId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter("author")
                .pathParameter(authorId)
                .pathParameter("genre")
                .pathParameter(genreId)
                .pathParameter("books");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Book.class);
    }

    ///api/library/genre/{genreId}/books
    //get all Books of special Genre
    @Step("Get all Books of special Genre.")
    public BaseResponse<Book> getBooksByGenreIdGet(ListOptions options, int genreId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter("genre")
                .pathParameter(genreId)
                .pathParameter("books");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Book.class);
    }

    ///api/library/books/search
    //search for books by book name, return first 5 the most relevant results
    @Step("Search for books by book name, return first 5 the most relevant results.")
    public BaseResponse<Book> getBooksSearchGet(ListOptions options, String queryWord) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter("books")
                .pathParameter("search");
        endpoint.queryParam("q", queryWord);
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Book.class);
    }
}
