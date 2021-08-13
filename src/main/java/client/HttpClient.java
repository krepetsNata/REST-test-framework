package client;

import config.ServiceConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.log4j.Logger;
import response.BaseResponse;

public class HttpClient {

    private static final Logger LOG = Logger.getLogger(BaseResponse.class);

    public static BaseResponse get(String endpoint) {
        return HttpClient.sendRequest(Method.GET, endpoint);
    }

    public static BaseResponse post(String endpoint, String body) {
        return HttpClient.sendRequest(Method.POST, endpoint, body);
    }

    public static BaseResponse put(String endpoint, String body) {
        return HttpClient.sendRequest(Method.PUT, endpoint, body);
    }

    public static BaseResponse delete(String endpoint) {
        return HttpClient.sendRequest(Method.DELETE, endpoint);
    }

    private static BaseResponse sendRequest(Method method, String endpoint) {
        return HttpClient.sendRequest(method, endpoint, null);
    }

    private static BaseResponse sendRequest(Method method, String endpoint, String body) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(ServiceConfig.HOST);
        builder.setBasePath(endpoint.concat("/"));
        builder.addHeader("Content-Type", "application/json");
        if (body != null) builder.setBody(body);
        RequestSpecification spec = builder.build();
        //Response rawResponse = spec.request(method);
        RequestSpecification specification = RestAssured.given(spec);
//        String url = ServiceConfig.HOST + endpoint;
//        RequestSpecification spec = RestAssured.given();
//        spec.header("Content-Type", "application/json");
//        if (body != null) spec.body(body);
       Response rawResponse = specification.request(method);

        LOG.info(System.out.format("\n\nREQUEST:\nmethod: %s\nuri: %s\nbody:\n%s\n", specification.request().log().method(), specification.log().uri(), specification.log().body()));
        LOG.info(System.out.format("\n\nRESPONSE:\nstatus line: %s\nbody:\n%s\n", rawResponse.getStatusLine(), rawResponse.getBody().asPrettyString()));


        return new BaseResponse(rawResponse);
    }
}
