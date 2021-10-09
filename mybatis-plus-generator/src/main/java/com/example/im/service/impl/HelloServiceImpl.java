package com.example.im.service.impl;

import com.example.im.entity.Hello;
import com.example.im.mapper.HelloMapper;
import com.example.im.service.IHelloService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhongjunjie
 * @since 2021-10-04
 */
@Service
public class HelloServiceImpl extends ServiceImpl<HelloMapper, Hello> implements IHelloService {

}
