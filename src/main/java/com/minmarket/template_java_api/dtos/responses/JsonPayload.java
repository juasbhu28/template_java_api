package com.minmarket.template_java_api.dtos.responses;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonPayload {

    public static final String PROTOCOL = "protocol";
    public static final String METHOD = "method";
    public static final String HEADERS = "headers";
    public static final String COOKIES = "cookies";
    public static final String PARAMETERS = "parameters";
    public static final String PATH = "path";
    public static final String BODY = "body";

    private final Map<String, Object> attributes = new HashMap<>();

    @JsonAnySetter
    public void set(String name, Object value) {
        attributes.put(name, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getAttributes() {
        return attributes;
    }

}
