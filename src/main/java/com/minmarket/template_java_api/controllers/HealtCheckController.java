package com.minmarket.template_java_api.controllers;


import com.minmarket.template_java_api.constants.RouteMapping;
import com.minmarket.template_java_api.dtos.responses.JsonPayload;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(RouteMapping.HEALTH_CHECK_API)
@Api(tags = RouteMapping.HEALTH_CHECK_API)
public class HealtCheckController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/echo", consumes = MediaType.ALL_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE,
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public ResponseEntity<Object> echoBack(@RequestBody(required = false) byte[] rawBody) {

        final Map<String, String> headers  = Collections.list(request.getHeaderNames()).stream()
                .collect(Collectors.toMap(Function.identity(), request::getHeader));

        final JsonPayload response = new JsonPayload();
        response.set(JsonPayload.PROTOCOL, request.getProtocol());
        response.set(JsonPayload.METHOD, request.getMethod());
        response.set(JsonPayload.HEADERS, headers);
        response.set(JsonPayload.COOKIES, request.getCookies());
        response.set(JsonPayload.PARAMETERS, request.getParameterMap());
        response.set(JsonPayload.PATH, request.getServletPath());
        response.set(JsonPayload.BODY, rawBody != null ? rawBody : null);
        log.info("REQUEST: {}", request.getParameterMap());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
