package com.crio.starter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import com.crio.starter.exceptions.MemeAlreadyExistsException;
import com.crio.starter.exceptions.MemeConstraintViolationException;
import com.crio.starter.exceptions.MemeNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MemeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleMemeAlreadyExistsException(MemeAlreadyExistsException e) {
        logger.error("Meme already exists exception: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(MemeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleMemeNotFoundException(MemeNotFoundException e) {
        logger.error("Meme not found exception: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> violations = e.getConstraintViolations()
                                    .stream()
                                    .map(ConstraintViolation::getMessageTemplate)
                                    .collect(Collectors.toList());
        logger.error("Constraint violation exception: {}", violations, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(joinWithNewLine(violations)));
    }

    @ExceptionHandler(MemeConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMemeConstraintViolationException(MemeConstraintViolationException e) {
        logger.error("Meme constraint violation exception: {}", e.getViolations(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(joinWithNewLine(e.getViolations())));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception e) {
        logger.error("Unknown exception occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("An unexpected error occurred. Please try again later."));
    }

    private String joinWithNewLine(List<String> messages) {
        return String.join("\n", messages);
    }

    public static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
    
}
