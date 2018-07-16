package edu.nikon.simpleapi.api.common.handler;

import edu.nikon.simpleapi.api.common.response.Response;
import edu.nikon.simpleapi.api.common.response.dto.ErrorDto;
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
     * Generates a response body with specified messages
     *
     * @param errorMessages error messages
     * @return response object
     */
    protected Response handleError(List<String> errorMessages) {
        ErrorDto error = new ErrorDto(errorMessages);
        logger.debug(error.toString());
        return Response.error(error);
    }

    /**
     * Generate a response body for internal server error
     *
     * @param e exception cause
     * @return response object
     */
    protected Response handleInternalError(Exception e) {
        logger.error("Internal server error", e);
        return handleError(Collections.singletonList("Internal server error"));
    }
}
