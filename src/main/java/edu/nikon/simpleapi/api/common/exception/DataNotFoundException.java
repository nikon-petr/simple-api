package edu.nikon.simpleapi.api.common.exception;

/**
 * Exception thrown
 */
public class DataNotFoundException extends ApiException {

    public DataNotFoundException() {
    }

    public DataNotFoundException(String s) {
        super(s);
    }

    public DataNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
