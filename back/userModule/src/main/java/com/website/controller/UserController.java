package com.website.controller;

//import com.alibaba.nacos.api.annotation.NacosInjected;
import com.website.dto.UserInfo;
import com.website.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user")
@RestController
@AllArgsConstructor
public class UserController {
//    @NacosInjected
    UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public UserInfo getUserInfoById(@PathVariable("id") int id){
        return userService.getUserInfoById(id);
    }

    @GetMapping("/{id}/name")
    @ResponseBody
    public String getUserName(@PathVariable("id") int id){
        return userService.getUserName(id);
    }
}
