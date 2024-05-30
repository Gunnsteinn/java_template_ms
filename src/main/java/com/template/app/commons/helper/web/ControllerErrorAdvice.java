package com.template.app.commons.helper.web;

import com.template.app.domain.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
public class ControllerErrorAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ControllerErrorAdvice.class);

    @ExceptionHandler(value = {ValidationException.class,
            ServiceException.class,
            NotFoundException.class,
            BadRequestException.class})
    public final ResponseEntity<ErrorResponse> handleCustomExceptions(ApiException ex) {
        return error(ex.getHttpStatus(), ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        var validationList = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        var validationMessage = String.join(", ", validationList);
        return error(HttpStatus.BAD_REQUEST, validationMessage);
    }

    @ExceptionHandler({ResponseStatusException.class})
    public final ResponseEntity<ErrorResponse> handleResponseStatusExceptions(
            ResponseStatusException ex) {
        return error(ex.getStatusCode(), ex.getReason());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "An error occurred while processing your request.";
        if (ex.getCause() instanceof ConstraintViolationException constraintEx) {
            if (constraintEx.getSQLException() != null && constraintEx.getSQLException().getMessage() != null) {
                message = "Duplicate entry detected: " + constraintEx.getSQLException().getMessage();
            }
            return error(HttpStatus.BAD_REQUEST, message);
        }
        return error(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return error(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    protected ResponseEntity<ErrorResponse> error(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), message));
    }

    protected ResponseEntity<ErrorResponse> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), e.getMessage()));
    }

    protected ResponseEntity<ErrorResponse> error(HttpStatus status, String code, String message) {
        return ResponseEntity.status(status)
                .body(new ErrorResponse(status.value(), status.getReasonPhrase(), code, message));
    }

    protected ResponseEntity<ErrorResponse> error(HttpStatusCode status, String message) {
        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), message));
    }
}
