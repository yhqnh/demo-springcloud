package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanghq on 2017/9/1.
 */
@RestController
@RefreshScope
public class YhqController {

    @Value("${github}")
    private String github;

    @Autowired
    private Environment environment;

    @RequestMapping("/getConfigFromValue")
    public String getConfigFromValue(){
        return this.github;
    }

    @RequestMapping("/getConfigFromEnviroment")
    public String getConfigFromEnviroment(){
        return environment.getProperty("github","我是默认值");
    }

    @RequestMapping("/getConfigFromArchars")
    public String getConfigFromArchars(){
        return "";
    }
}
