package az.unitech.auth.exception;

import org.springframework.http.HttpStatus;

public interface ErrorResponse {
    String getKey();
    String getMessage();
    HttpStatus getHttpStatus();
}
