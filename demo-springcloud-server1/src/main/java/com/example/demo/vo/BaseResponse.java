package com.example.demo.vo;

import lombok.Data;
import java.util.List;

/**
 * @author yhq
 * @create 2017-08-03 5:25 PM
 **/
@Data
public class BaseResponse<T> {

    private int code;

    private String message;


    private List<T> data;
}
