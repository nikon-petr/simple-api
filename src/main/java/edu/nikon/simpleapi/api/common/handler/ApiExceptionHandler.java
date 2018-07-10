package edu.nikon.simpleapi.api.common.handler;

import edu.nikon.simpleapi.api.common.exception.DataConflictException;
import edu.nikon.simpleapi.api.common.exception.DataNotFoundException;
import edu.nikon.simpleapi.api.common.response.Response;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Order(2)
public class ApiExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response handleDataNotFoundException(DataNotFoundException e, HttpServletRequest req) {
        String errorMessage = String.format("Resource not found %s", req.getRequestURI());
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(DataConflictException.class)
    @ResponseStatus(CONFLICT)
    public Response handleDataConflictException(DataConflictException e) {
        return handleError(Collections.singletonList(e.getMessage()));
    }
}
