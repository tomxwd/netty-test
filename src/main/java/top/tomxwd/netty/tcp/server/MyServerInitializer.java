package top.tomxwd.netty.tcp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.net.Socket;

/**
 * @author xieweidu
 * @createDate 2019-12-26 22:35
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new MyServerHandler());
    }
}
