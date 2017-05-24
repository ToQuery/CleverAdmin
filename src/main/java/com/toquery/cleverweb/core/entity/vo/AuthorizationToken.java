package com.toquery.cleverweb.core.entity.vo;

/**
 * 用户操作token
 *
 * @author ToQuery
 * @version V1.0
 * @date 2017/5/22 15:42
 */
public class AuthorizationToken {

    private String userName;
    private String userNickName = "CleverWeb";
    private String userToken;

    public AuthorizationToken() {
    }

    public AuthorizationToken(String userToken) {
        this.userToken = userToken;
    }

    public AuthorizationToken(String userName, String userToken) {
        this.userName = userName;
        this.userToken = userToken;
    }

    public AuthorizationToken(String userName, String userNickName, String userToken) {
        this.userName = userName;
        this.userNickName = userNickName;
        this.userToken = userToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
