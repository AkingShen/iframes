package com.iframe.interfaces.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;


public class CommonDto implements Serializable {

    private String token;

    private String userId;

    public CommonDto() {
        super();
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
