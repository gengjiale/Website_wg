package com.website.entity;

import lombok.Data;

@Data
public class User {
    String userId;
    String name;
    String password;
    String avatar;
    String email;
    String phone;
}
