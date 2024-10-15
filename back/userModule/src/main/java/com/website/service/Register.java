package com.website.service;

import com.website.dto.UserInfo;

public class Register {

    public UserInfo register1(UserInfo userInfo_request){
        return  userInfo_request;
    }
    public UserInfo register2(String type, String unionid, UserInfo userInfo_request){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(type);
        return userInfo;
    }
}
