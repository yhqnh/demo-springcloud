package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhq
 * @create 2017-08-07 2:10 PM
 **/
@RestController
@Slf4j
public class YhqController {

    @RequestMapping("/hello")
    private String hello(){
        return "client2";
    }

    @RequestMapping("/helloParam")
    private String helloParam(String name){
        return "helloParam2" + name;
    }
}
