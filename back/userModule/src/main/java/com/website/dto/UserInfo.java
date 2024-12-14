package com.website.dto;

import lombok.Data;

@Data
public class UserInfo {
    String userId;
    String name;
    String password;
    Byte[] avatar;
    String email;
    String phone;

}
