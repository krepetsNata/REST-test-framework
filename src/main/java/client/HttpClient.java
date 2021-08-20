package client;

import config.ServiceConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import java.util.stream.Collectors;

public class HttpClient {

    private static final Logger LOG = Logger.getLogger(HttpClient.class);

    public static Response get(String endpoint) {
        return HttpClient.sendRequest(Method.GET, endpoint);
    }

    public static Response post(String endpoint, String body) {
        return HttpClient.sendRequest(Method.POST, endpoint, body);
    }

    public static Response put(String endpoint, String body) {
        return HttpClient.sendRequest(Method.PUT, endpoint, body);
    }

    public static Response delete(String endpoint) {
        return HttpClient.sendRequest(Method.DELETE, endpoint);
    }

    private static Response sendRequest(Method method, String endpoint) {
        return HttpClient.sendRequest(method, endpoint, null);
    }

    @Attachment
    private static Response sendRequest(Method method, String endpoint, String body) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(ServiceConfig.HOST);
        builder.setBasePath(endpoint.concat("/"));
        builder.addHeader("Content-Type", "application/json");
        if (body != null) builder.setBody(body);

        RequestSpecification spec = builder.build().given();
        Response rawResponse = RestAssured.given(spec).request(method);

        //***logging***//
        FilterableRequestSpecification httpRequest = (FilterableRequestSpecification) RestAssured.given(spec);
        String contentQueryParams = httpRequest.getQueryParams().entrySet()
                .stream()
                .map(e -> e.getKey() + "=\"" + e.getValue() + "\"")
                .collect(Collectors.joining(", "));
        String myRequest = String.format("\n\nREQUEST:\nmethod: %s\nuri: %s\nqueryParam: %s\nbody:\n%s\n", method, httpRequest.getBaseUri()+httpRequest.getBasePath(), contentQueryParams, httpRequest.getBody());
        String myResponse = String.format("\n\nRESPONSE:\nstatus line: %s\nbody:\n%s\n", rawResponse.getStatusLine(), rawResponse.getBody().asPrettyString());

        Allure.addAttachment("My request", myRequest);
        Allure.addAttachment("My response", myResponse);

        LOG.info(myRequest);
        LOG.info(myResponse);

        return rawResponse;
    }
}
