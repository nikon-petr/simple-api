package edu.nikon.simpleapi.api.common;

import edu.nikon.simpleapi.api.common.dto.ErrorDto;
import edu.nikon.simpleapi.api.common.dto.ResponseWrapperDto;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = "edu.nikon.simpleapi.api")
public class ResponseBodyAdviceImpl implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Object.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        ResponseWrapperDto<Object> resp = new ResponseWrapperDto<>();
        if (body instanceof ErrorDto) {
            ErrorDto errorDto = (ErrorDto) body;
            resp.setError(errorDto.getMessage());
        } else {
            resp.setData(body);
        }
        return resp;
    }
}
