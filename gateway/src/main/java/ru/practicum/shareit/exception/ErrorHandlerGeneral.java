package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandlerGeneral {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerValidationException(ValidationException exception) {
        log.warn("409 {}", exception.getMessage());
        return new ErrorResponse("Validation error 409", exception.getMessage());
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorResponse handlerNotFoundException(ObjectNotFoundException exception) {
//        log.warn("404 {}", exception.getMessage());
//        return new ErrorResponse("Object not found 404", exception.getMessage());
//    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerInvalidStatusException(InvalidStatusException exception) {
        log.warn("400 {}", exception.getMessage());
        return new ErrorResponse(exception.getMessage(), "Invalid status 400");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({DataExistException.class})
    public ErrorResponse handlerDataExistException(DataExistException exception) {
        log.warn(exception.getClass().getSimpleName(), exception);
        return new ErrorResponse("409 Conflict", exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerDuplicateException(DuplicateEmailException exception) {
        log.warn("409 {}", exception.getMessage());
        return new ErrorResponse("DuplicateEmailException error 409", exception.getMessage());
    }
}
