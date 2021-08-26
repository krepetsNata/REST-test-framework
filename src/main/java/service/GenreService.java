package service;

import client.HttpClient;
import entity.ListOptions;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import pojo.Genre;
import response.BaseResponse;
import utils.EndpointBuilder;

import java.util.HashMap;
import java.util.List;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class GenreService {

    private static final Logger LOG = Logger.getLogger(GenreService.class);

    ///api/library/genre
    //create new Genre
    @Step("Create new Genre.")
    public BaseResponse<Genre> addGenrePost(Genre genre) {
        String endpoint = new EndpointBuilder().pathParameter("genre").get();
        return new BaseResponse<>(HttpClient.post(endpoint, genre.toString()), Genre.class);
    }

    ///api/library/genre
    //get Genre object by 'genreId'
    @Step("Get Genre object by 'genreId'.")
    public BaseResponse<Genre> updateGenrePut(Genre author) {
        String endpoint = new EndpointBuilder().pathParameter("genre").get();
        return new BaseResponse<>(HttpClient.put(endpoint, author.toString()), Genre.class);
    }

    ///api/library/genre/{genreId}
    //get Genre object by 'genreId'
    @Step("Get Genre object by 'genreId'.")
    public BaseResponse<Genre> getGenreByGenreIdGet(int genreId) {
        String endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).get();
        return new BaseResponse<>(HttpClient.get(endpoint), Genre.class);
    }

    ///api/library/genres
    //get all Genres
    @Step("Get all Genres.")
    public BaseResponse<Genre> getGenres(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("genres");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Genre.class);
    }

    ///api/library/genre/{genreId}
    //delete existed Genre
    @Step("Delete existed Genre.")
    public BaseResponse<Genre> deleteGenreDelete(int genreId) {
        String endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).get();
        return new BaseResponse<>(HttpClient.delete(endpoint), Genre.class);
    }

    ///api/library/author/{authorId}/genres
    //get all Genres of special Author
    @Step("Get all Genres of special Author.")
    public BaseResponse<Genre> getGenresByAuthorIdGet(ListOptions options, int authorId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter("author")
                .pathParameter(authorId)
                .pathParameter("genres")
                .addListOptions(options);
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Genre.class);
    }

    ///api/library/book/{bookId}/genre
    //get Genre of special Book
    @Step("Get Genre of special Book.")
    public BaseResponse<Genre> getGenreByBookIdGet(ListOptions options, int bookId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter("book")
                .pathParameter(bookId)
                .pathParameter("genre")
                .addListOptions(options);

        return new BaseResponse<>(HttpClient.get(endpoint.get()), Genre.class);
    }

    ///api/library/genres/search
    //search for genre by it genre name
    @Step("Search for genre by it genre name.")
    public BaseResponse<Genre> getGenresSearchGet(ListOptions options, String queryWord) {
        HashMap<String, String> queryParams = new HashMap<>();

        if (!queryWord.trim().isEmpty() && queryWord.length() >= 3) {
            queryParams.put("query", queryWord);
        } else {
            LOG.error(queryWord, new Exception("queryParam is wrong - it should be more than 3 symbols and not empty!"));
        }

        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter("genres")
                .pathParameter("search")
                .addListOptions(options);

        return new BaseResponse(HttpClient.get(endpoint.get(), queryParams), Genre.class);
    }

    /**
     * Method returns actual Genre object from response
     *
     * @param baseResponse wrapper for Response which we got
     * @return actual Genre object from response
     */
    @Step("Return actual Genre object from response")
    public static Genre getActualObjGenre(BaseResponse baseResponse) {
        Response response = baseResponse.getResponse();
        Genre actualObj = null;
        if (response.getStatusCode() != SC_NOT_FOUND)
            actualObj = response.jsonPath().getObject(".", Genre.class);
        return actualObj;
    }

    @Step("Return actual list of Genres from response.")
    public List<Genre> getActualListGenres(BaseResponse baseResponse) {
        Response response = baseResponse.getResponse();
        List<Genre> actualList = response.jsonPath().getList(".", Genre.class);
        return actualList;
    }

    @Step("Return bool type - true if word contains in all items of list and false if this is not.")
    public boolean isContainedQueryWordInGenresList(List<Genre> genresList, String queryWord) {
        boolean isContains = false;
        for (Genre genre : genresList) {
            isContains = genre.getGenreName().contains(queryWord);
            if (!isContains) break;
        }
        return isContains;
    }
}
