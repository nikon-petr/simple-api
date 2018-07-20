package edu.nikon.simpleapi.api.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.nikon.simpleapi.api.common.response.dto.ErrorDto;
import edu.nikon.simpleapi.api.common.response.dto.OperationResultDto;
import io.swagger.annotations.ApiModel;

/**
 * Base REST API response
 *
 * @param <T> data type
 */
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

    /**
     * Returns response with specified result
     *
     * @param result operation result
     * @return response with specified result
     */
    public static Response<OperationResultDto> operationResult(OperationResults result) {
        return new Response<>(new OperationResultDto(result.getMessage()));
    }

    /**
     * Returns response with specified error body
     *
     * @param errorDto error body
     * @return response with specified error body
     */
    public static Response error(ErrorDto errorDto) {
        return new Response(errorDto);
    }

    /**
     * Returns response with specified data body
     *
     * @param dataDto data body
     * @param <R>     data body type
     * @return response with specified data body
     */
    public static <R> Response<R> data(R dataDto) {
        return new Response<>(dataDto);
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
