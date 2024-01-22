package az.unitech.auth.exception.error;


import org.springframework.http.HttpStatus;

import az.unitech.auth.exception.ErrorResponse;

public enum Errors implements ErrorResponse {
    PIN_EXIST( "PIN_EXIST", HttpStatus.BAD_REQUEST , "User with this pin is exist"),
    PIN_NOT_FOUND( "PIN_NOT_FOUND", HttpStatus.UNAUTHORIZED , "User with this pin not found"),
    INCORRECT_PASSWORD("INCORRECT_PASSWORD", HttpStatus.FORBIDDEN,"Password is incorrect!!!"),
    UNSUPPORTED_JWT("UNSUPPORTED_TOKEN", HttpStatus.FORBIDDEN, "Token is invalid");
    String key;
    HttpStatus httpStatus;
    String message;


    Errors(String key, HttpStatus httpStatus, String message) {
        this.message = message;
        this.key = key;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}

