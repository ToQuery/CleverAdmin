package com.toquery.cleverweb.core.exception;


public class CleverWebException extends Exception {
    public CleverWebException() {
        super();
    }

    public CleverWebException(String message) {
        super(message);
    }

    public CleverWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public CleverWebException(Throwable cause) {
        super(cause);
    }

    protected CleverWebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
