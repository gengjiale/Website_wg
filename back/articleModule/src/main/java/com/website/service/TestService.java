package com.website.service;


import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.AllArgsConstructor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@AllArgsConstructor
public class TestService {

    private NacosServiceManager nacosServiceManager;
    private RestTemplate restTemplate;

    public String test(){
        restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        List<Instance> instanceList;
        NamingService namingService = nacosServiceManager.getNamingService();
        try {
            instanceList = namingService.getAllInstances("user");
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
        String id = instanceList.get(0).getIp();
        int port = instanceList.get(0).getPort();
        String getUserNameUrl =  "http://" + id + ":" + port + "/api/v1/user/1/name";
        System.out.println(getUserNameUrl);
        String result = restTemplate.getForObject(getUserNameUrl, String.class);
        return result;
    }
}
