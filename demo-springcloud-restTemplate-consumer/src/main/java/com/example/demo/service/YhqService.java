package com.example.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "ribbonConsumerFallback")
    public String ribbonConsumer(){
        return restTemplate.getForEntity("http://demo-springcloud-client/hello",String.class).getBody();
    }

    public String ribbonConsumerFallback(){
        return "error";
    }
}
