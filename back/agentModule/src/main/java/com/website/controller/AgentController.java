//package com.website.controller;
//
//import com.website.service.AgentService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@AllArgsConstructor
//public class AgentController {
//    AgentService agentService;
//
//    @RequestMapping(value = "/api/**",
//            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
//    public HttpServletResponse agent(HttpServletRequest request){
//       return agentService.agent(request);
//    }
//}
