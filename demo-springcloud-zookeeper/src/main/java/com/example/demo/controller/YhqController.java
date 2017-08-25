package com.example.demo.controller;

import com.example.demo.vo.BaseResponse;
import com.example.demo.vo.People;
import io.swagger.annotations.ApiOperation;
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
public class YhqController {

    @RequestMapping("/swagger")
    @ApiOperation(value = "value")
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
}
