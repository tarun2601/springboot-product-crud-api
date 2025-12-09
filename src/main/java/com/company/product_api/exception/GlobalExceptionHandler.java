package com.company.product_api.exception;

import com.company.product_api.dto.JsonResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /*---------ConstraintViolationException----------- */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<JsonResponse<String>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.info("In Constraint Violation Exception {}",ex.getMessage());
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .reduce((m1, m2) -> m1 + ", " + m2)
                .orElse("Validation failed");

        JsonResponse<String> response = new JsonResponse<>("Validation Error");
        response.setData(errorMessage);
        response.setSuccess(false);
        response.setDebugMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    /*--------ResourceNotFoundException--------------- */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<JsonResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.info("In Resource Not Found Exception {}",ex.getMessage());
        JsonResponse<String> response = new JsonResponse<>("Resource Not Found");
        response.setSuccess(false);
        response.setDebugMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    /* -------@Valid Method Argument Errors----------------- */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JsonResponse<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.info("In Handle Validation Exception {}",ex.getMessage());
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Validation failed");

        JsonResponse<String> response = new JsonResponse<>("Validation Failed");
        response.setData(errorMessage);
        response.setSuccess(false);
        response.setDebugMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    /*--------Generic Exception--------------- */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonResponse<String>> handleGenericException(Exception ex) {
        log.info("In Generic Exception {}",ex.getMessage());
        JsonResponse<String> response = new JsonResponse<>("Something went wrong !", ex);
        response.setSuccess(false);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
