package com.website.service;

import com.website.dto.UserInfo;

public class Login {
    public UserInfo loginByNP(String name, String password){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        return  userInfo;
    }
    public UserInfo loginByTU(String type, String unionid){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(type);
        return userInfo;
    }
}
