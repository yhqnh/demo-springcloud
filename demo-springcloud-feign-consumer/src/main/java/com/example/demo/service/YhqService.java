package com.example.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yhq
 * @create 2017-08-18 3:30 PM
 **/
@FeignClient("demo-springcloud-client")
public interface YhqService {

    @RequestMapping("/hello")
    public String hello();

    @RequestMapping("/helloParam")
    public String helloParam(@RequestParam("name") String name);
}
