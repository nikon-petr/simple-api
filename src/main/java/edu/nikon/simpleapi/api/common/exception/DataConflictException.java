package edu.nikon.simpleapi.api.common.exception;

public class DataConflictException extends ApiException {

    public DataConflictException() {
    }

    public DataConflictException(String s) {
        super(s);
    }

    public DataConflictException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
