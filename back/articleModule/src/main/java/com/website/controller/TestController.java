package com.website.controller;


import com.website.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/test")
@RestController
@AllArgsConstructor
public class TestController {
    TestService testService;

    @GetMapping
    @ResponseBody
    public String getUserInfoById(){
        return testService.test();
    }
}
