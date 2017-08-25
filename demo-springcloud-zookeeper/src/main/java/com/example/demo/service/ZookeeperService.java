package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yhq
 * @create 2017-07-21 10:02 AM
 **/
@Component
@EnableScheduling
@Slf4j
public class ZookeeperService {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Scheduled(fixedDelay = 10000)
    public String test(){
        log.info(">>>test is start");
        List<String> list = discoveryClient.getServices();
        log.info(">>>{}",discoveryClient.getInstances("192.168.1.235"));
        log.info(">>>{}",discoveryClient.getInstances("STORES"));
        for (String s : list) {
            log.info(s);
        }

        configurationService.getZookeeperConfig();
        return null;
    }

}
