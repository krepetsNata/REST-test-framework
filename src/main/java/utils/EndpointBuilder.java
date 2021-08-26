package utils;

import entity.ListOptions;

public class EndpointBuilder {
    private String endpoint;

    public EndpointBuilder() {
        this.endpoint = "/api/library";
    }

    public EndpointBuilder pathParameter(String param) {
        this.endpoint += "/" + param;
        return this;
    }

    public EndpointBuilder pathParameter(int param) {
        return this.pathParameter(String.valueOf(param));
    }

    public EndpointBuilder queryParam(String param, String value) {
        String delimiter;
        if (this.endpoint.contains("?")) delimiter = "&";
        else delimiter = "?";
        this.endpoint += delimiter + param + "=" + value;
        return this;
    }

    public EndpointBuilder queryParam(String param, int value) {
        return this.queryParam(param, String.valueOf(value));
    }

    public EndpointBuilder queryParam(String param, boolean value) {
        return this.queryParam(param, String.valueOf(value));
    }

    public EndpointBuilder addListOptions(ListOptions options) {
        if (options.orderType != null) this.queryParam("orderType", options.orderType);
        this
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) this.queryParam("sortBy", options.sortBy);
        return this;
    }

    public EndpointBuilder addListOptions(ListOptions options, int page, int size) {
        if (options.orderType != null) this.queryParam("orderType", options.orderType);
        this
                .queryParam("page", options.setPage(page).page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.setSize(size).size);
        if (options.sortBy != null) this.queryParam("sortBy", options.sortBy);
        return this;
    }

    public String get() {
        return this.endpoint;
    }

}