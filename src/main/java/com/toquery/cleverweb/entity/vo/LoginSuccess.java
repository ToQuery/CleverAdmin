package com.toquery.cleverweb.entity.vo;

/**
 * @author ToQuery
 * @version V1.0
 * @date 2017/5/22 15:42
 */
public class LoginSuccess {

    private String userName;
    private String userNickName;
    private String userToken;

    public LoginSuccess() {
    }

    public LoginSuccess(String userName, String userNickName, String userToken) {
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
