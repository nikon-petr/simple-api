package edu.nikon.simpleapi.api.common.handler;

import edu.nikon.simpleapi.api.common.response.Response;
import edu.nikon.simpleapi.api.common.response.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public abstract class AbstractExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(AbstractExceptionHandler.class);

    protected Response handleError(List<String> errorMessages) {
        ErrorDto error = new ErrorDto(errorMessages);
        logger.debug(error.toString());
        return Response.error(error);
    }

    protected Response handleInternalError(Exception e) {
        logger.error("Internal server error", e);
        return handleError(Collections.singletonList("Internal server error"));
    }
}
