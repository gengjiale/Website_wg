package com.website.service;

import com.website.dto.UserInfo;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    public UserInfo getUserInfoById(int id){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        return userInfo;
    }

    public String getUserName(int id){
        return "testName";
    }
}
