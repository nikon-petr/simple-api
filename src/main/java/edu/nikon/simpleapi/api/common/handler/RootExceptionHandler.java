package edu.nikon.simpleapi.api.common.handler;

import edu.nikon.simpleapi.api.common.response.Response;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Root exception handler
 */
@RestControllerAdvice
@Order(3)
public class RootExceptionHandler extends AbstractExceptionHandler {

    /**
     * Handle any types of exception if no special handler is found
     *
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class, RuntimeException.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public Response handleDefaultException(Exception e) {
        return handleInternalError(e);
    }
}
