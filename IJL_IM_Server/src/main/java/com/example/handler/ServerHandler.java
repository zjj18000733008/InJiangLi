package com.example.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entity.Ack;
import com.example.enums.MessageType;
import com.example.protobuf.MessageProto;
import com.example.service.UserConnStatusService;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/5
 */
@Slf4j
@Component
public class ServerHandler extends SimpleChannelInboundHandler<MessageProto.Message> {

    @Autowired
    private UserConnStatusService userConnStatusService;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.info("handler added");
        Ack ack = Ack.builder().msgId(1L).build();
        ctx.writeAndFlush(ack);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProto.Message message) throws Exception {
        log.info("channel read0");
        if (MessageType.GREET.value().equals(message.getType())) {
            log.info("received message {}",message);
            userConnStatusService.online(message.getFromId(), ctx);

            MessageProto.Ack ack = MessageProto.Ack.newBuilder().setMsgId(message.getId()).build();
            ctx.writeAndFlush(ack);
            return;
        }
        System.out.println(message);
    }


}
