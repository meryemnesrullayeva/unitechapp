package az.unitech.app.errors;

import az.unitech.app.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public enum ErrorsFinal implements ErrorResponse {
    DATA_NOT_FOUND( "DATA_NOT_FOUND", HttpStatus.NOT_FOUND, "bu id-li '{id}' '{name}' məlumat tapılmadı"),
    DATA_NOT_FOUND_LAST( "DATA_NOT_FOUND_LAST", HttpStatus.NOT_FOUND, "{message}"),
    BAD_REQUEST( "BAD_REQUEST", HttpStatus.BAD_REQUEST, "daxil edilən parametr(lər) yanlışdır: {message}"),
    INTERNAL_SERVER_ERROR( "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "daxili server xətası"),
    SERVICE_TYPE_ERROR("SERVICE_TYPE_ERROR", HttpStatus.BAD_REQUEST, "{message}"),
    PARAM_IN_USE("PARAM_IN_USE", HttpStatus.BAD_REQUEST, "parametrin istifadə edildiyi dəyər mövcuddur"),
    
    
    ACCOUNT_NUMBER_EXISTS( "ACCOUNT_NUMBER_EXISTS", HttpStatus.BAD_REQUEST, "Account Number exist: '{accountNumber}'"),
    SAME_ACCOUNT( "SAME_ACOOUNT", HttpStatus.BAD_REQUEST, "Account numbers are same"),
    ACCOUNT_NUMBER_NOT_FOUND( "ACCOUNT_NUMBER_NOT_FOUND", HttpStatus.NOT_FOUND, "Account Number not found : '{accountNumber}'"),
    ACCOUNT_NUMBER_IS_DEACTIVE( "ACCOUNT_NUMBER_IS_DEACTIVE", HttpStatus.NOT_FOUND, "Account Number is deactive : '{accountNumber}'"),
    WRONG_AMOUNT( "WRONG_AMOUNT", HttpStatus.BAD_REQUEST, "Wrong amount"),
    NOT_ENOUGH_BALANCE( "NOT_ENOUGH_BALANCE", HttpStatus.BAD_REQUEST, "Not enough balance"),
    CURRENCY_SERVICE_ERROR( "CURRENCY_SERVICE_ERROR", HttpStatus.BAD_REQUEST, "Currency service not response"),

    //for testing exceptions
    EXCEPTION_IN_TESTING("EXCEPTION_IN_TESTING", HttpStatus.INTERNAL_SERVER_ERROR, "testing zamanı xəta yarandı"),

    //security exception messages
    ACCESS_DENIED("ACCESS_DENIED",HttpStatus.FORBIDDEN,"İcazə yoxdur"),
    EXPIRED_JWT_ERROR("EXPIRED_JWT_ERROR", HttpStatus.UNAUTHORIZED, "JWT token-in vaxtı keçib"),
    UNSUPPORTED_JWT_ERROR("UNSUPPORTED_JWT_ERROR",HttpStatus.UNAUTHORIZED,"Bu token formatı dəstəklənmir"),
    MALFORMED_JWT_ERROR("MALFORMED_JWT_ERROR",HttpStatus.UNAUTHORIZED,"Token düzgün formatda deyil"),
    SIGNATURE_JWT_ERROR("SIGNATURE_JWT_ERROR",HttpStatus.UNAUTHORIZED,"Token-in signature - i səhvdi"),
    INVALID_TOKEN("INVALID_TOKEN",HttpStatus.UNAUTHORIZED,"Token səhvdi"),

    //for standard exception messages
    UNIQUE_CONSTRAINT( "23505", HttpStatus.BAD_REQUEST, "təkrarlana bilməz"),
    FK_CONSTRAINT( "23503", HttpStatus.BAD_REQUEST, "əlaqəli olduğu məlumat xətası"),
    NOT_EMPTY_CONSTRAINT( "23502", HttpStatus.BAD_REQUEST, "boş ola bilməz"),
    CHECK_VIOALATION( "23514", HttpStatus.BAD_REQUEST, "check violation xətası");

    final String key;
    final HttpStatus httpStatus;
    final String message;

    ErrorsFinal(String key, HttpStatus httpStatus, String message) {
        this.key = key;
        this.httpStatus = httpStatus;
        this.message = message;
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
