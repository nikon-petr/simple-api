package edu.nikon.simpleapi.api.common.handler;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(2)
public class ApiExceptionHandler extends AbstractExceptionHandler {

}
