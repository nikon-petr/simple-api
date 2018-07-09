package edu.nikon.simpleapi.api.common.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import edu.nikon.simpleapi.api.common.dto.ApiResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class SpringExceptionHandler extends AbstractExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(SpringExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorMessages = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> String.format("Invalid field: %s, cause: %s", fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        logger.debug(e.getBindingResult().getGlobalErrors().toString());
        return handleError(errorMessages);
    }

    @ExceptionHandler(MismatchedInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto handleInvalidFormatException(MismatchedInputException e) {
        String fieldName = e.getPath().stream()
                .map(reference -> reference.getFieldName())
                .reduce((fullName, name) -> fullName + "." + name)
                .get();
        String fieldTargetTypeName = e.getTargetType().getSimpleName().toLowerCase();
        String errorMessage = String.format("Field %s is not valid for type %s", fieldName, fieldTargetTypeName);
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto handleUnrecognizedPropertyException(UnrecognizedPropertyException e) {
        String fieldName = e.getPath().stream()
                .map(reference -> reference.getFieldName())
                .reduce((fullName, name) -> fullName + "." + name)
                .get();
        String errorMessage = String.format("Field %s is unknown", fieldName);
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto handleJsonMappingException(JsonParseException e) {
        return handleError(Collections.singletonList("Invalid syntax"));
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto missingPathVariableException(MissingPathVariableException e) {
        String errorMessage = String.format("Path variable %s not found", e.getVariableName());
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String parameterTypeName = e.getParameter().getParameterType().toString().toLowerCase();
        String errorMessage = String.format("Path variable %s is not valid for type %s", e.getName(), parameterTypeName);
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponseDto missingServletRequestParameter(MissingServletRequestParameterException e) {
        String errorMessage = String.format("Request parametr %s is required", e.getParameterName());
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponseDto handleNoHandlerFoundException(NoHandlerFoundException e) {
        String errorMessage = String.format("Resource %s not found", e.getRequestURL());
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ApiResponseDto handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        String errorMessage = "Media type is not supported";
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ApiResponseDto> httpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        String errorMessage = "Media type is not acceptable";
        HttpHeaders headers = new HttpHeaders();
        if (!e.getSupportedMediaTypes().isEmpty()) {
            headers.add("Accept", MediaType.toString(e.getSupportedMediaTypes()));
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .headers(headers)
                .body(handleError(Collections.singletonList(errorMessage)));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponseDto> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String errorMessage = String.format("Method %s not allowed", e.getMethod());
        HttpHeaders headers = new HttpHeaders();
        if (e.getSupportedMethods().length > 0) {
            headers.add("Allow", String.join(",", e.getSupportedMethods()));
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .headers(headers)
                .body(handleError(Collections.singletonList(errorMessage)));
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponseDto handleHttpMessageNotWritableException(HttpMessageNotWritableException e) {
        return handleInternalError();
    }
}
