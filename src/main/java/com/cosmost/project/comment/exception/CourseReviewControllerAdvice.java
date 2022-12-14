package com.cosmost.project.comment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CourseReviewControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String processValidationError(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();
        FieldError fieldError = bindingResult.getFieldError();
        builder.append(fieldError.getDefaultMessage());

        return builder.toString();
    }

    @ExceptionHandler(CourseParamNotFoundException.class)
    public ResponseEntity<String> CourseParamNotFoundException(CourseParamNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(CourseReviewIdNotFoundException.class)
    public ResponseEntity<String> CourseReviewIdNotFoundException(CourseReviewIdNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> CourseNotFoundException(CourseNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(WriteReviewNotFoundException.class)
    public ResponseEntity<String> WriteReviewNotFoundException(WriteReviewNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(ReportAnswerNotFoundException.class)
    public ResponseEntity<String> ReportAnswerNotFoundException(ReportAnswerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(DupulicateCourseReviewException.class)
    public ResponseEntity<String> DupulicateCourseReviewException(DupulicateCourseReviewException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }



}
