package com.example.server;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import com.example.handler.ServerHandler;
import com.example.protobuf.MessageProto;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/5
 */
@Slf4j
@Component
public class IMServer implements ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware {

    private Integer serverPort=6668;
    private ApplicationContext applicationContext;
    private Channel serverChannel;
    private static volatile IMServer instance;

    @Autowired
    private ServerHandler serverHandler;

    private IMServer(){

    }

    public static void main(String[] args) {
        IMServer.getInstance().start();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(8);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("decoder",new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
                            pipeline.addLast("encoder",new ProtobufEncoder());
                            pipeline.addLast(serverHandler);
                        }
                    });
            log.info("server is ready");
            ChannelFuture cf = bootstrap.bind(serverPort).sync();
            serverChannel=cf.channel();
            cf.addListener(future -> {
                if (future.isSuccess()) {
                    log.info("IMServer端口{}绑定成功",serverPort);
                }else{
                    log.error("IMServer端口{}绑定失败",serverPort);
                }
            });

            cf.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        if (this.serverChannel != null) {
            this.serverChannel.close();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public static IMServer getInstance(){

        if (instance == null) {
            synchronized(IMServer.class){
                if (instance == null) {
                    instance =new IMServer();
                }
            }
        }
        return instance;
    }



    public void start(){

    }

}
