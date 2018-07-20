package edu.nikon.simpleapi.api.common.handler;

import edu.nikon.simpleapi.api.common.response.Response;
import edu.nikon.simpleapi.api.common.response.dto.ErrorDto;
import edu.nikon.simpleapi.api.common.response.dto.OperationResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Abstract exception handler
 */
public abstract class AbstractExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(AbstractExceptionHandler.class);

    /**
     * Wrap error messages to ErrorDto
     *
     * @param errorMessages error messages
     * @return error dto
     */
    protected ErrorDto handleError(List<String> errorMessages) {
        return new ErrorDto(errorMessages);
    }

    /**
     * Wrap error messages to ErrorDto with result
     *
     * @param errorMessages error messages
     * @return response object
     */
    protected Response<OperationResultDto> handleOerationError(List<String> errorMessages) {
        return Response.errorOperation(new ErrorDto(errorMessages));
    }

    /**
     * Generate a response body for internal server error
     *
     * @param e exception cause
     * @return error dto
     */
    protected ErrorDto handleInternalError(Exception e) {
        logger.error("Internal server error", e);
        return handleError(Collections.singletonList("Internal server error"));
    }
}
