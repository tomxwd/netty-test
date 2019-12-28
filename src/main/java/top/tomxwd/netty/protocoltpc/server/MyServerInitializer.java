package top.tomxwd.netty.protocoltpc.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import top.tomxwd.netty.protocoltpc.codec.MyMessageDecode;
import top.tomxwd.netty.protocoltpc.codec.MyMessageEncode;

/**
 * @author xieweidu
 * @createDate 2019-12-26 22:35
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                // 解码器
                .addLast(new MyMessageDecode())
                // 编码器
                .addLast(new MyMessageEncode())
                .addLast(new MyServerHandler());
    }
}
