package com.website.service;

import com.website.dto.UserInfo;

public class UserInfoService {

    public UserInfo getUserInfoById(String id){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        return userInfo;
    }

    public String getUserName(String id){
        return "testName";
    }

    public UserInfo updateUserInfo(String id, UserInfo userInfo){
        return userInfo;
    }
}
