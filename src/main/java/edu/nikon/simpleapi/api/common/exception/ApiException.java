package edu.nikon.simpleapi.api.common.exception;

/**
 * Base exception thrown business logic errors in API
 */
public class ApiException extends RuntimeException {

    public ApiException() {
    }

    public ApiException(String s) {
        super(s);
    }

    public ApiException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
