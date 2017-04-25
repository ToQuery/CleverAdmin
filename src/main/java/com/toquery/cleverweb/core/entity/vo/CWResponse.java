package com.toquery.cleverweb.core.entity.vo;

/**
 * Created by ToQuery on 2016-08-20.
 */
public class CWResponse<T> {

    private String code = "";
    private String msg = "";
    private T body ;

    public CWResponse() {
    }

    public CWResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CWResponse(String code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
