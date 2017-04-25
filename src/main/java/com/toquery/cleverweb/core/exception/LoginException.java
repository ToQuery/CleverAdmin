package com.toquery.cleverweb.core.exception;

import com.toquery.cleverweb.core.entity.vo.CWResponse;

/**
 * Created by ToQuery on 2016-08-20.
 */
public class LoginException extends BaseException {

    private CWResponse response = new CWResponse();

    public LoginException(CWResponse response) {
        this.response = response;
    }

    public LoginException(String code, String msg) {
        this.response = new CWResponse(code, msg);
    }

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    protected LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    public CWResponse getResponse() {
        return response;
    }

    public void setResponse(CWResponse response) {
        this.response = response;
    }
}
