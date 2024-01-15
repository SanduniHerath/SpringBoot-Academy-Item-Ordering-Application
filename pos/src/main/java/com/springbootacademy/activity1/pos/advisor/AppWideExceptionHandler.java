package com.springbootacademy.activity1.pos.advisor;

import com.springbootacademy.activity1.pos.exception.NotFoundException;
import com.springbootacademy.activity1.pos.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Error coming",e.getMessage() + " Sanduni"),
                HttpStatus.NOT_FOUND
        );
    }
}
