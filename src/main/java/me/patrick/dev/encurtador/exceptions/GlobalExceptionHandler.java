package me.patrick.dev.encurtador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionResponse handleUrlNotFoundException(UrlNotFoundException ex) {
        return ExceptionResponse.builder().message(ex.getMessage()).error(true).build();
    }

}