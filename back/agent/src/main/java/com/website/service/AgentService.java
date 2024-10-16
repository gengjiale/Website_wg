package com.website.service;

import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AgentService {
    private NacosServiceManager nacosServiceManager;
    public String agent(HttpServletRequest request){
        //获取URI，并从URI中获取服务名
        String uri = request.getRequestURI();
        String[] s = uri.split("/");
        if(s.length >= 4){
            String serviceName = s[3];
            //根据服务名向ncos获取虚拟ip和端口
            List<Instance> instanceList;
            NamingService namingService = nacosServiceManager.getNamingService();
            try {
                instanceList = namingService.getAllInstances(serviceName);
            } catch (NacosException e) {
                throw new RuntimeException(e);
            }
            for(int i = 0; i < instanceList.size(); i++){
                if(instanceList.get(i).isHealthy()){ // 找到健康的服务
                    Instance instance = instanceList.get(i);
                    String ip = instance.getIp();
                    int port = instance.getPort();
                    //获取URL中的参数，并和 IP 端口拼接成新的URL
                    String queryString = request.getQueryString();
                    return "http://" + ip + ":" + port + "?" + queryString;
                }
            }
        }
        return null;
    }
}
