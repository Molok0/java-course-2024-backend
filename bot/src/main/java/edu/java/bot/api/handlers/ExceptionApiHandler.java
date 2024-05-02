package edu.java.bot.api.handlers;

import edu.java.bot.ApiErrorResponse;
import edu.java.bot.api.controllers.BotController;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackageClasses = {BotController.class})
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
