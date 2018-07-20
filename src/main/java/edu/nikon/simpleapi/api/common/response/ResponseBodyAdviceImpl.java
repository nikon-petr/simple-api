package edu.nikon.simpleapi.api.common.response;

import edu.nikon.simpleapi.api.common.response.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "edu.nikon.simpleapi.api")
public class ResponseBodyAdviceImpl implements ResponseBodyAdvice<Object> {

    private final Logger logger = LoggerFactory.getLogger(ResponseBodyAdviceImpl.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Object.class.isAssignableFrom(returnType.getParameterType()) &&
                !ResponseEntity.class.isAssignableFrom(returnType.getParameterType()) &&
                !Response.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        logger.debug("Response: {}", body);

        if (body instanceof ErrorDto) {
            return Response.error((ErrorDto) body);
        }

        return Response.data(body);
    }
}
