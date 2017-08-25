package com.example.demo.service;


import com.netflix.config.DynamicProperty;
import com.netflix.config.DynamicPropertyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConfigurationService {


    public String getZookeeperConfig() {
        String test = DynamicPropertyFactory.getInstance().getStringProperty("yhq", "null").get();
        log.info(">>>yhq is {}", test);
        return test;
    }

}
