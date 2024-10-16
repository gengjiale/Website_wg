package com.website.controller;

import com.website.service.AgentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AgentController {
    AgentService agentService;

    @RequestMapping(value = "/api/**",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public String agent(HttpServletRequest request,@RequestBody Object requestBody){
       String URL_new = agentService.agent(request);
       if (URL_new != null){
           return "redirect:"+ URL_new ;
       }else {
           return null;
       }
    }
}
