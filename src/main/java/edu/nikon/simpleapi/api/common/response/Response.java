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
     * Return wrapped success operation result
     *
     * @return success {@link OperationResultDto}
     */
    public static Response<OperationResultDto> successOperation() {
        return new Response<>(new OperationResultDto("success"));
    }

    /**
     * Return {@link ErrorDto} and {@link OperationResultDto} wrapped in {@link Response}
     *
     * @param errorDto error dto
     * @return error operation result
     */
    public static Response<OperationResultDto> errorOperation(ErrorDto errorDto) {
        Response<OperationResultDto> response = new Response<>(new OperationResultDto("error"));
        response.setError(errorDto);
        return response;
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

    private void setData(T data) {
        this.data = data;
    }

    public ErrorDto getError() {
        return error;
    }

    private void setError(ErrorDto error) {
        this.error = error;
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
