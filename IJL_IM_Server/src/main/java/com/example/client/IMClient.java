package com.example.client;

import java.util.Scanner;

import com.example.client.handler.ClientHandler;
import com.example.enums.MessageType;
import com.example.protobuf.MessageProto;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/5
 */
public class IMClient {

    private static Integer serverPort = 6668;

    public static void main(String[] args) {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("decoder",new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
                            pipeline.addLast("encoder",new ProtobufEncoder());
                            pipeline.addLast(new ClientHandler());
                        }
                    });
            ChannelFuture cf = bootstrap.connect("127.0.0.1", serverPort).sync();

            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String next = sc.nextLine();
                MessageProto.Message message = MessageProto.Message.newBuilder()
                        .setId(1L).setType(MessageType.NORMAL.value())
                        .setFromId(1L).setToId(2L).setContent(next).build();
                cf.channel().writeAndFlush(message);
            }

            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }

}
