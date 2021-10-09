package com.example.im.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.ApiResponse;
import com.example.im.entity.Hello;
import com.example.im.service.IHelloService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhongjunjie
 * @since 2021-10-04
 */
@RestController
@RequestMapping("/im/hello")
public class HelloController {

    @Value("${injiangli.im.server.port:6668}")
    private Integer serverPort;

    @Autowired
    private IHelloService helloService;

    @GetMapping("/1")
    public String hello(){
        Hello helloDO = helloService.getById(1);
        return helloDO.getName();
    }

    @GetMapping("/2")
    public ApiResponse test(){
        return ApiResponse.success(serverPort);
    }

}
