package edu.nikon.simpleapi.api.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

@ApiModel("ApiResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T> {

    private T data;
    private ErrorDto error;

    public ApiResponseDto(T data) {
        this.data = data;
    }

    public ApiResponseDto(ErrorDto error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorDto getError() {
        return error;
    }

    public void setError(ErrorDto error) {
        this.error = error;
    }
}
