package com.example.demo;

import com.netflix.config.DynamicPropertyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Configuration
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class ZookeeperApplication {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discovery;

    @Autowired
    private Environment env;

    @Autowired
    private AppClient appClient;


    @Autowired
    private OwnerProperties ownerProperties;

    @RequestMapping("/")
    public ServiceInstance lb() {
        return this.loadBalancer.choose(this.appName);
    }

    @RequestMapping("/hi")
    public String hi() {
        return "Hello World! from " + this.discovery.getLocalServiceInstance();
    }

    @RequestMapping("/self")
    public String self() {
        return this.appClient.hi();
    }

    @RequestMapping("/getFromEnv")
    public String getFromEnv(@RequestParam("prop") String prop) {
        return new RelaxedPropertyResolver(this.env).getProperty(prop, "Not Found");
    }

    @RequestMapping("/getFromArchaius")
    public String getFromArchaius(@RequestParam("prop") String prop){
        return DynamicPropertyFactory.getInstance().getStringProperty(prop,"null").get();
    }

    @RequestMapping("/getFromConfigProperties")
    public String getFromConfigProperties(){
        return ownerProperties.getFirstName();
    }

    @FeignClient("demo-springcloud-zookeeper")
    interface AppClient {
        @RequestMapping(path = "/hi", method = RequestMethod.GET)
        String hi();
    }

    @Autowired
    RestTemplate rest;

    public String rt() {
        return this.rest.getForObject("http://" + this.appName + "/hi", String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }
}
