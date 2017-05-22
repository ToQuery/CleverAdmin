package com.toquery.cleverweb.entity.dto;

/**
 * @author ToQuery
 * @version V1.0
 * @date 2017/5/22 14:45
 */
public class UserLogin {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
