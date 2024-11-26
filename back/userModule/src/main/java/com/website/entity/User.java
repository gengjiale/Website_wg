package com.website.entity;

import lombok.Data;

@Data
public class User {
    int id;
    String name;
    String password;
    String avatar;
    String email;
    String phone;
}
