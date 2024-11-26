package com.website.service;

import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.website.dto.ArticleInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CollectService {
    private NacosServiceManager nacosServiceManager;
    private RestTemplate restTemplate;

    /**
     * 获取用户收藏文章列表
     * 向文章模块要
     * @param userid
     * @return
     */
    public List<ArticleInfo> getCollectList(int userid){
        restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
//        List<Instance> instanceList;
//        NamingService namingService = nacosServiceManager.getNamingService();
//        try {
//            instanceList = namingService.getAllInstances("article");
//        } catch (NacosException e) {
//            throw new RuntimeException(e);
//        }
//        String id = instanceList.get(0).getIp();
//        int port = instanceList.get(0).getPort();
//        String url =  "http://" + id + ":" + port + "/api/article/user/" + userid +"/collect";
        String url = "http://localhost:8082/api/article/user/"+userid+"/collect";
        List result = restTemplate.getForObject(url, List.class);
        return result;
    }

    /**
     * 收藏文章
     * @param userid
     * @param articleid
     * @return
     */
    public ArticleInfo collectArticle(int userid,String articleid){
        restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
//        List<Instance> instanceList;
//        NamingService namingService = nacosServiceManager.getNamingService();
//        try {
//            instanceList = namingService.getAllInstances("article");
//        } catch (NacosException e) {
//            throw new RuntimeException(e);
//        }
//        String id = instanceList.get(0).getIp();
//        int port = instanceList.get(0).getPort();
//        String url =  "http://" + id + ":" + port + "/api/article/user/"+userid+"/collect/"+articleid;
        String url = "http://localhost:8082/api/article/user/"+userid+"/collect/"+articleid;
        ArticleInfo result = restTemplate.getForObject(url, ArticleInfo.class);
        return result;
    }

    /**
     * 取消收藏
     * @param userid
     * @param articleid
     */
    public void cancelCollect(int userid,String articleid){
        restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
//        List<Instance> instanceList;
//        NamingService namingService = nacosServiceManager.getNamingService();
//        try {
//            instanceList = namingService.getAllInstances("article");
//        } catch (NacosException e) {
//            throw new RuntimeException(e);
//        }
//        String id = instanceList.get(0).getIp();
//        int port = instanceList.get(0).getPort();
//        String url =  "http://" + id + ":" + port + "/api/article/user/"+userid+"/collect/"+articleid;
        String url = "http://localhost:8082/api/article/user/"+userid+"/collect/"+articleid;
//        ArticleInfo result = restTemplate.getForObject(getUserNameUrl, ArticleInfo.class);
        restTemplate.delete(url);
    }
}
