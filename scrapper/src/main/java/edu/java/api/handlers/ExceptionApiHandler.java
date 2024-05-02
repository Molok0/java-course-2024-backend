package edu.java.api.handlers;

import edu.java.api.controllers.TgChatController;
import edu.java.api.controllers.UrlController;
import edu.java.generation.ApiErrorResponse;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {TgChatController.class, UrlController.class})
@Slf4j
public class ExceptionApiHandler {
    private static final String DESCRIPTION_NULL_POINTER = "Не хватает параметров в теле запроса";
    private static final String DESCRIPTION_NOT_READABLE = "Неправильное тело запроса";

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiErrorResponse> notFoundException(NullPointerException exception) {
        log.info(DESCRIPTION_NULL_POINTER);
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ApiErrorResponse()
                .exceptionMessage(exception.getMessage())
                .exceptionName(exception.getClass().getName())
                .code(String.valueOf(HttpStatus.NOT_FOUND))
                .description(DESCRIPTION_NULL_POINTER)
                .stacktrace(Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.info(DESCRIPTION_NOT_READABLE);
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ApiErrorResponse()
                .exceptionMessage(exception.getMessage())
                .exceptionName(exception.getClass().getName())
                .code(String.valueOf(HttpStatus.BAD_REQUEST))
                .description(DESCRIPTION_NOT_READABLE)
                .stacktrace(Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList()));
    }
}
