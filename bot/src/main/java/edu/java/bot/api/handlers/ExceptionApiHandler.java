package edu.java.bot.api.handlers;

import edu.java.bot.ApiErrorResponse;
import edu.java.bot.api.controllers.BotController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {BotController.class})
public class ExceptionApiHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> notFoundException(Exception exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ApiErrorResponse()
                .exceptionMessage(exception.getMessage())
                .exceptionName(exception.getClass().getName())
                .code(String.valueOf(HttpStatus.BAD_REQUEST)));
    }
}
