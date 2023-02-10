package com.minmarket.template_java_api.exceptions;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException{
    private final int status;
    private final String message;

    public GeneralException(int status, String message){
        this.status = status;
        this.message = message;
    }
}
