package edu.nikon.simpleapi.api.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.nikon.simpleapi.api.common.response.dto.ErrorDto;
import edu.nikon.simpleapi.api.common.response.dto.OperationResultDto;
import io.swagger.annotations.ApiModel;

@ApiModel("Response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T data;
    private ErrorDto error;

    private Response(T data) {
        this.data = data;
    }

    private Response(ErrorDto error) {
        this.error = error;
    }

    public static Response<OperationResultDto> operationResult(OperationResults result) {
        return new Response<>(new OperationResultDto(result.getMessage()));
    }

    public static Response error(ErrorDto errorDto) {
        return new Response(errorDto);
    }

    public static <R> Response<R> data(R data) {
        return new Response<>(data);
    }

    public T getData() {
        return data;
    }

    public ErrorDto getError() {
        return error;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Response{");
        sb.append("data=").append(data);
        sb.append(", error=").append(error);
        sb.append('}');
        return sb.toString();
    }
}
