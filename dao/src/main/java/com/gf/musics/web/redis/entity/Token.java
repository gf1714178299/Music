package com.gf.musics.web.redis.entity;

import java.io.Serializable;

/**
 * Created by chenpan on 2017/5/25.
 */
public class Token implements Serializable {

    private static final long serialVersionUID = -7276287768724977737L;

    private String token;

    private Integer userId;

    private String account;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
