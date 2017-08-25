package com.example.demo.controller;

import com.example.demo.vo.BaseResponse;
import com.example.demo.vo.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.Log4jConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhq
 * @create 2017-08-07 2:10 PM
 **/
@RestController
@Slf4j
public class YhqController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/swagger")
    public BaseResponse<List<People>> swagger(@RequestParam("prop") String prop){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("test");
        List list = new ArrayList();
        People people = new People();
        people.setName("people");
        people.setSex("nan");
        list.add(people);
        baseResponse.setData(list);
        return baseResponse;
    }

    @RequestMapping("/index")
    private String index(){
        List<String> serviceList = discoveryClient.getServices();
        serviceList.forEach(service -> {
            log.info("service is {}",service);
        });
        return "index";
    }

    @RequestMapping("/hello")
    private String hello(){
        return "client2";
    }
}
