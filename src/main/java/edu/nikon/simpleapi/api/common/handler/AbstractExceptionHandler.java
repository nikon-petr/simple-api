package edu.nikon.simpleapi.api.common.handler;

import edu.nikon.simpleapi.api.common.dto.ApiResponseDto;
import edu.nikon.simpleapi.api.common.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public abstract class AbstractExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(AbstractExceptionHandler.class);

    protected ApiResponseDto handleError(List<String> errorMessages) {
        ErrorDto error = new ErrorDto(errorMessages);
        logger.debug(error.toString());
        return new ApiResponseDto(error);
    }

    protected ApiResponseDto handleInternalError() {
        return handleError(Collections.singletonList("Internal server error"));
    }
}
