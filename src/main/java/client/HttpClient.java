package client;

import config.ServiceConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.PrintStream;

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

        //PrintStream logStream = LOG.IoBuilder.forLogger(logger).buildPrintStream();
        RequestSpecification spec = builder
                //.addFilter(new RequestLoggingFilter())// RequestLoggingFilter.logRequestTo(logStream))
                //.addFilter(new ResponseLoggingFilter())// ResponseLoggingFilter.logResponseTo(logStream))
                .build().given();
        RequestSpecification specification = RestAssured.given(spec);
        Response rawResponse = specification.request(method);


        Allure.addAttachment("My attachment", rawResponse.getBody().asPrettyString());

        LOG.info(System.out.format("\n\nREQUEST:\nmethod: %s\nuri: %s\nbody:\n%s\n", specification.log().method(), specification.log().uri(), specification.log().body()));
        LOG.info(System.out.format("\n\nRESPONSE:\nstatus line: %s\nlocation: %s\nbody:\n%s\n", rawResponse.getStatusLine(), rawResponse.getHeader("Location"), rawResponse.getBody().asPrettyString()));

        return rawResponse;
    }
}
