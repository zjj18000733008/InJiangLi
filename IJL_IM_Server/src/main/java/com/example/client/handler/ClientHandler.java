package com.example.client.handler;

import com.example.enums.MessageType;
import com.example.protobuf.MessageProto;

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
public class ClientHandler extends SimpleChannelInboundHandler<MessageProto.Message> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client channel active");
        MessageProto.Message message = MessageProto.Message.newBuilder()
                .setId(1L).setType(MessageType.GREET.value())
                .setFromId(1L).setToId(2L).setContent("hello").build();
        ctx.writeAndFlush(message);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProto.Message message) throws Exception {
        log.info("channel read");
        System.out.println(message);
    }
}
