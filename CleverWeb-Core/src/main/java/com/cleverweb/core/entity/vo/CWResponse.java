package com.cleverweb.core.entity.vo;

/**
 * Created by ToQuery on 2016-08-20.
 */
public class CWResponse {

    private String code = "";
    private String msg = "";
    private String body = "";

    public CWResponse() {
    }

    public CWResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CWResponse(String code, String msg, String body) {
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
