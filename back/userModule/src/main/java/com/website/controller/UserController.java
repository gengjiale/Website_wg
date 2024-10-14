package com.website.controller;

import com.website.dto.UserInfo;
import com.website.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user")
@RestController
@AllArgsConstructor
public class UserController {

    UserService userService;

    /**
     * 根据用户id获取对应用户的全部信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public UserInfo getUserInfoById(@PathVariable("id") int id){
        return userService.getUserInfoById(id);
    }

    /**
     * 根据用户id获取对应用户的姓名
     * @param id
     * @return
     */
    @GetMapping("/{id}/name")
    @ResponseBody
    public String getUserName(@PathVariable("id") int id){
        return userService.getUserName(id);
    }

    /**
     * 注册
     * @param name
     * @param password
     * @param type
     * @param unionid
     * @return
     */
    @PostMapping
    @ResponseBody
    public UserInfo regist(@RequestParam(required = false) String name, @RequestParam(required = false) String password,
                           @RequestParam(required = false) String type, @RequestParam(required = false) String unionid){
        return userService.register(name, password, type, unionid);
    }


}
