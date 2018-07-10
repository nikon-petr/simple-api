package edu.nikon.simpleapi.api.common.response;

public enum OperationResults {
    SUCCESS("success"),
    ERROR("error");

    private String message;

    OperationResults(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
