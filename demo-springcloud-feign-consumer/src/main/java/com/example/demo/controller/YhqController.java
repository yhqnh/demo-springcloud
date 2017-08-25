package com.example.demo.controller;

import com.example.demo.service.YhqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhq
 * @create 2017-08-07 2:10 PM
 **/
@RestController
@Slf4j
public class YhqController {

    @Autowired
    private YhqService yhqService;

    @RequestMapping("/feignConsumer")
    private String feignConsumer(){
        return yhqService.hello();
    }
}
