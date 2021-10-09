package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.common.exception.BusinessRuntimeException;

import io.netty.channel.ChannelHandlerContext;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/5
 */
@Component
public class UserConnStatusService {

    private static Map<Long,ChannelHandlerContext> userConnMap=new HashMap<>();

    public void online(Long userId, ChannelHandlerContext ctx){
        userConnMap.putIfAbsent(userId, ctx);
    }

    public void offline(Long userId){
        userConnMap.remove(userId);
    }

    public Map<Long,ChannelHandlerContext> getUserConnMap(){
        return userConnMap;
    }

    public ChannelHandlerContext getUserConn(Long userId){
        if (userConnMap.containsKey(userId)) {
            return userConnMap.get(userId);
        }else{
            throw new BusinessRuntimeException("userId为{}的用户不存在!");
        }
    }

}
