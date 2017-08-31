package com.example.demo.service;

import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @author yhq
 * @create 2017-08-18 3:30 PM
 **/
@Service
public class YhqService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "restTemplateConsumerFallback",commandProperties ={@HystrixProperty(name = "fallback.enabled",value = "true")})
    public String restTemplateConsumer(){
        return restTemplate.getForEntity("http://demo-springcloud-client/hello",String.class).getBody();
    }

    public String restTemplateConsumerFallback(){
        return "我是降级逻辑";
    }
}
