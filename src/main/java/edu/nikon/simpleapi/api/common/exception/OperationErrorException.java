package edu.nikon.simpleapi.api.common.exception;

public class OperationErrorException extends ApiException{

    public OperationErrorException() {
    }

    public OperationErrorException(String s) {
        super(s);
    }

    public OperationErrorException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
