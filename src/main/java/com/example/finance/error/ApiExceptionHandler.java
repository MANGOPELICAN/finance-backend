package com.example.finance.error;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Converts common exceptions into clean JSON error responses.
 */
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    /** Immutable payload returned on every error */
    public record ApiError(
            Instant               timestamp,
            int                   status,
            String                error,
            String                message,
            Map<String, String>   fieldErrors) { }

    /* ---------- 400: bean-validation failures on @RequestBody ---------- */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = ex.getBindingResult()
                                            .getFieldErrors()
                                            .stream()
                                            .collect(Collectors.toMap(
                                                    fe -> fe.getField(),
                                                    fe -> fe.getDefaultMessage(),
                                                    (a, b) -> a)); // keep first dup

        return build(HttpStatus.BAD_REQUEST, "Validation failed", fieldErrors);
    }

    /* ---------- 400: bean-validation failures on @PathVariable / @RequestParam ---------- */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handlePathParam(ConstraintViolationException ex) {

        Map<String,String> fieldErrors = ex.getConstraintViolations()
                                           .stream()
                                           .collect(Collectors.toMap(
                                                   cv -> cv.getPropertyPath().toString(),
                                                   cv -> cv.getMessage(),
                                                   (a,b)->a));

        return build(HttpStatus.BAD_REQUEST, "Validation failed", fieldErrors);
    }

    /* ---------- 409: DB unique-constraint violations ---------- */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleIntegrity(DataIntegrityViolationException ex) {

        // Check the root cause message if you want finer control.
        return build(HttpStatus.CONFLICT, "Database constraint violated", null);
    }

    /* ---------- status errors we raise ourselves ---------- */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> handleStatus(ResponseStatusException ex) {

        HttpStatusCode status = ex.getStatusCode();
        String reason = (status instanceof HttpStatus hs)
                        ? hs.getReasonPhrase()
                        : status.toString();

        return build(status, ex.getReason() != null ? ex.getReason() : reason, null);
    }

    /* ---------- 500: any uncaught error ---------- */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleOther(Exception ex) {
        log.error("Unhandled exception", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
    }

    /* ---------- helper ---------- */
    private ResponseEntity<ApiError> build(HttpStatusCode status,
                                           String message,
                                           Map<String,String> fieldErrors) {

        ApiError body = new ApiError(
                Instant.now(),
                status.value(),
                (status instanceof HttpStatus hs) ? hs.getReasonPhrase() : status.toString(),
                message,
                fieldErrors
        );
        return ResponseEntity.status(status).body(body);
    }
}
