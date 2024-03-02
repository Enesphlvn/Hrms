package com.hrms.core.utilities.advice;

import com.hrms.core.utilities.results.ErrorDataResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDataResult<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorDataResult<Object> error = new ErrorDataResult<>(null, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDataResult<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorDataResult<Object> error = new ErrorDataResult<>(null, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDataResult<Object>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorDataResult<Object> error = new ErrorDataResult<>(null, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
