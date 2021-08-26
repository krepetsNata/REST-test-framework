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

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HttpClient {

    private static final Logger LOG = Logger.getLogger(HttpClient.class);

    public static Response get(String endpoint) {
        return HttpClient.sendRequest(Method.GET, endpoint);
    }

    public static Response get(String endpoint, HashMap<String, String> queryParams) {
        return HttpClient.sendRequest(Method.GET, endpoint, null, queryParams);
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
        return HttpClient.sendRequest(method, endpoint, null, null);
    }

    private static Response sendRequest(Method method, String endpoint, String body) {
        return HttpClient.sendRequest(method, endpoint, body, null);
    }

    private static Response sendRequest(Method method, String endpoint, HashMap<String, String> queryParams) {
        return HttpClient.sendRequest(method, endpoint, null, queryParams);
    }

    @Attachment
    private static Response sendRequest(Method method, String endpoint, String body, HashMap<String, String> queryParams) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(ServiceConfig.HOST);
        builder.setBasePath(endpoint.concat("/"));
        builder.addHeader("Content-Type", "application/json");

        if (body != null) builder.setBody(body);

        RequestSpecification spec = builder.build().given();

        if (!queryParams.isEmpty()) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                spec.queryParam(entry.getKey(), entry.getValue());
            }
        }

        Response rawResponse = RestAssured.given(spec).request(method);

        //***logging***//
        FilterableRequestSpecification httpRequest = (FilterableRequestSpecification) given(spec);

        String myRequest = String.format("\n\nREQUEST:\nmethod: %s\nheaders: %s\nuri: %s\nqueryParam: %s\nbody:\n%s\n",
                method, httpRequest.getHeaders(), httpRequest.getURI(), httpRequest.getQueryParams(), httpRequest.getBody());
        String myResponse = String.format("\n\nRESPONSE:\nstatus line: %s\nheaders: %s\nbody:\n%s\n",
                rawResponse.getStatusLine(), rawResponse.getHeaders(), rawResponse.getBody().asPrettyString());

        Allure.addAttachment("My request", myRequest);
        Allure.addAttachment("My response", myResponse);

        LOG.info(myRequest);
        LOG.info(myResponse);

        return rawResponse;
    }
}
