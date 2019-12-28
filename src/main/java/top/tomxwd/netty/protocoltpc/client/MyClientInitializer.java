package top.tomxwd.netty.protocoltpc.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import top.tomxwd.netty.protocoltpc.codec.MyMessageDecode;
import top.tomxwd.netty.protocoltpc.codec.MyMessageEncode;

/**
 * @author xieweidu
 * @createDate 2019-12-26 21:41
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new MyMessageEncode())
                .addLast(new MyMessageDecode())
                .addLast(new MyClientHandler());
    }
}
