package edu.nikon.simpleapi.api.common.handler;

import edu.nikon.simpleapi.api.common.dto.ApiResponseDto;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(3)
public class RootExceptionHandler extends AbstractExceptionHandler {
    @ExceptionHandler({Exception.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponseDto handleDefaultException(Exception e) {
        return handleInternalError(e);
    }
}
