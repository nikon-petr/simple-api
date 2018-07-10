package edu.nikon.simpleapi.api.common.response.dto;

import io.swagger.annotations.ApiModel;

@ApiModel("OperationResult")
public class OperationResultDto {

    private String result;

    public OperationResultDto() {
    }

    public OperationResultDto(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
