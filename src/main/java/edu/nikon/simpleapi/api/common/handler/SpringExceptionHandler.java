package edu.nikon.simpleapi.api.common.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import edu.nikon.simpleapi.api.common.response.Response;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
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

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

@RestControllerAdvice
@Order(1)
public class SpringExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorMessages = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getDefaultMessage())
                .collect(Collectors.toList());
        return handleError(errorMessages);
    }

    @ExceptionHandler(MismatchedInputException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleInvalidFormatException(MismatchedInputException e) {
        String fieldName = e.getPath().stream()
                .map(reference -> reference.getFieldName())
                .reduce((fullName, name) -> fullName + "." + name)
                .orElse("unknown");
        String fieldTargetTypeName = e.getTargetType().getSimpleName().toLowerCase();
        String errorMessage = String.format("Field %s is not valid for type %s", fieldName, fieldTargetTypeName);
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleUnrecognizedPropertyException(UnrecognizedPropertyException e) {
        String fieldName = e.getPath().stream()
                .map(reference -> reference.getFieldName())
                .reduce((fullName, name) -> fullName + "." + name)
                .orElse("unknown");
        String errorMessage = String.format("Field %s is unknown", fieldName);
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleJsonMappingException(JsonParseException e) {
        return handleError(Collections.singletonList("Invalid syntax"));
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response missingPathVariableException(MissingPathVariableException e) {
        String errorMessage = String.format("Path variable %s not found", e.getVariableName());
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String parameterTypeName = e.getParameter().getParameterType().toString().toLowerCase();
        String errorMessage = String.format("Path variable %s is not valid for type %s", e.getName(), parameterTypeName);
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response missingServletRequestParameter(MissingServletRequestParameterException e) {
        String errorMessage = String.format("Request parameter %s is required", e.getParameterName());
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response handleNoHandlerFoundException(NoHandlerFoundException e) {
        String errorMessage = String.format("Resource %s not found", e.getRequestURL());
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(UNSUPPORTED_MEDIA_TYPE)
    public Response handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        String errorMessage = "Media type is not supported";
        return handleError(Collections.singletonList(errorMessage));
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    public ResponseEntity<Response> httpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        String errorMessage = "Media type is not acceptable";
        HttpHeaders headers = new HttpHeaders();
        if (!e.getSupportedMediaTypes().isEmpty()) {
            headers.add("Accept", MediaType.toString(e.getSupportedMediaTypes()));
        }
        return ResponseEntity.status(NOT_ACCEPTABLE)
                .headers(headers)
                .body(handleError(Collections.singletonList(errorMessage)));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Response> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String errorMessage = String.format("Method %s not allowed", e.getMethod());
        HttpHeaders headers = new HttpHeaders();
        if (e.getSupportedMethods().length > 0) {
            headers.add("Allow", String.join(",", e.getSupportedMethods()));
        }
        return ResponseEntity.status(METHOD_NOT_ALLOWED)
                .headers(headers)
                .body(handleError(Collections.singletonList(errorMessage)));
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public Response handleHttpMessageNotWritableException(HttpMessageNotWritableException e) {
        return handleInternalError(e);
    }
}
