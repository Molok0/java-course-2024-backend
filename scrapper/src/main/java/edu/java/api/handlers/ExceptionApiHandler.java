package edu.java.api.handlers;

import edu.java.api.controllers.ScrapperController;
import edu.java.scrapper.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {ScrapperController.class})
public class ExceptionApiHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> notFoundException(Exception exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ApiErrorResponse().exceptionMessage(exception.getMessage())
                .exceptionName(exception.getClass().getName())
                .code(String.valueOf(HttpStatus.BAD_REQUEST)));
    }
}
