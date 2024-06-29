package dev.chol.anyquizai.web;

import dev.chol.anyquizai.exception.CategoryNotFoundException;
import dev.chol.anyquizai.exception.NickNameTakenException;
import dev.chol.anyquizai.exception.QuizNotFoundException;
import dev.chol.anyquizai.exception.SavePhotoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandlerAdvice {
    @ExceptionHandler(CategoryNotFoundException.class)
    ProblemDetail handleNotFoundException(CategoryNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(QuizNotFoundException.class)
    ProblemDetail handleNotFoundException(QuizNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(SavePhotoException.class)
    ProblemDetail handleImageSaveException(SavePhotoException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(NickNameTakenException.class)
    ProblemDetail handleNickNameTakenException(NickNameTakenException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}