package com.website.service;

import com.website.dto.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    Register register = new Register();
    public UserInfo getUserInfoById(int id){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        return userInfo;
    }

    public String getUserName(int id){
        return "testName";
    }

    public UserInfo register(String name, String password, String type, String uniond){
        if(name != null && password != null && type == null && uniond == null){
            // 账号密码登录
            return register.registerByNP(name, password);
        }else if(name == null && password == null && type != null && uniond != null){
            // 其他方式登录
            return register.registerByTU(type, uniond);
        }else {
            // 报错
            return null;
        }
    }
}
