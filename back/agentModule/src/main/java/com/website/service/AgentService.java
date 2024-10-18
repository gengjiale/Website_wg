//package com.website.service;
//
//import com.alibaba.cloud.nacos.NacosServiceManager;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.api.naming.NamingService;
//import com.alibaba.nacos.api.naming.pojo.Instance;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class AgentService {
//    private NacosServiceManager nacosServiceManager;
//    private RestTemplate restTemplate;
//    public HttpServletResponse agent(HttpServletRequest request){
//        //获取URI，并从URI中获取服务名
//        String uri = request.getRequestURI();
//        String[] s = uri.split("/");
//        if(s.length >= 4){
//            String serviceName = s[3];
//            //根据服务名向ncos获取虚拟ip和端口
//            restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
//            List<Instance> instanceList;
//            NamingService namingService = nacosServiceManager.getNamingService();
//            try {
//                instanceList = namingService.getAllInstances(serviceName);
//            } catch (NacosException e) {
//                throw new RuntimeException(e);
//            }
//            for(int i = 0; i < instanceList.size(); i++){
//                if(instanceList.get(i).isHealthy()){ // 找到健康的服务
//                    Instance instance = instanceList.get(i);
//                    String ip = instance.getIp();
//                    int port = instance.getPort();
//                    //获取URL中的参数，并和 IP 端口拼接成新的URL
//                    String queryString = request.getQueryString();
//                    String URL_new = "http://" + ip + ":" + port + "?" + queryString;
//
//                    /**
//                     * 现在确定，必须用restTemplate去获取服务
//                     * 用它获取服务需要：1）URL    已有
//                     *               2) 请求头   貌似比较好获取
//                     *               3）请求体   不太确定该怎么获取
//                     *               4）返回类型  比较粗暴地设为Object 看看行不行
//                     * 除此之外，就是如何把这些东西加到restTemplate上面
//                     *                1） getForEntity postForEntity put delete
//                     *                2) exchange()
//                     * 之后就试一试哪个可以吧
//                     */
//
//                }
//            }
//        }
//        return null;
//    }
//}
