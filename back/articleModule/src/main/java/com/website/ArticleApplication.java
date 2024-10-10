package com.website;

//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(value = "com.website.mapper")
//@EnableDiscoveryClient
public class ArticleApplication {
    public static void main(String[] args){
        SpringApplication.run(ArticleApplication.class, args);
    }
}
