package za.co.lindaring.gay.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import za.co.lindaring.gay.exception.QuestionNotFoundException;
import za.co.lindaring.gay.exception.TechnicalException;
import za.co.lindaring.gay.exception.UserNotFoundException;

@ControllerAdvice
public class GayTestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { UserNotFoundException.class, QuestionNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(Exception e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionHandlerResponse bodyOfResponse = ExceptionHandlerResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(e.getMessage())
                .exception(e.getClass()).build();
        return handleExceptionInternal(e, bodyOfResponse, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(value = { TechnicalException.class })
    protected ResponseEntity<Object> handleTechnical(TechnicalException e, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionHandlerResponse bodyOfResponse = ExceptionHandlerResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(e.getMessage())
                .exception(e.getClass()).build();
        return handleExceptionInternal(e, bodyOfResponse, new HttpHeaders(), status, request);
    }

}
