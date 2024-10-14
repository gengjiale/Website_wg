package com.website.service;

import com.website.dto.UserInfo;

public class Register {

    public UserInfo registerByNP(String name, String password){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        return  userInfo;
    }
    public UserInfo registerByTU(String type, String unionid){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(type);
        return userInfo;
    }
}
