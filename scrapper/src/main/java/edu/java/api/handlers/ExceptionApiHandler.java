package edu.java.api.handlers;

import edu.java.api.controllers.TgChatController;
import edu.java.api.controllers.UrlController;
import edu.java.generation.ApiErrorResponse;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {TgChatController.class, UrlController.class})
public class ExceptionApiHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiErrorResponse> notFoundException(NullPointerException exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ApiErrorResponse()
                .exceptionMessage(exception.getMessage())
                .exceptionName(exception.getClass().getName())
                .code(String.valueOf(HttpStatus.NOT_FOUND))
                .description("Не хватает параметров в теле запроса")
                .stacktrace(Arrays.stream(exception.getStackTrace()).map(e -> e.toString()).toList()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ApiErrorResponse()
                .exceptionMessage(exception.getMessage())
                .exceptionName(exception.getClass().getName())
                .code(String.valueOf(HttpStatus.BAD_REQUEST))
                .description("Неправильное тело запроса")
                .stacktrace(Arrays.stream(exception.getStackTrace()).map(e -> e.toString()).toList()));
    }
}
