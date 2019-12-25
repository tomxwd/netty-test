package top.tomxwd.netty.inboundandoutboundhandler.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import top.tomxwd.netty.inboundandoutboundhandler.codec.MyByteToLongDecoder;
import top.tomxwd.netty.inboundandoutboundhandler.codec.MyLongToByteEncoder;

/**
 * @author xieweidu
 * @createDate 2019-12-25 20:52
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 对于服务器来说，客户端发送long到服务器，入站操作，需要解码，
        pipeline.addLast(new MyByteToLongDecoder())
                .addLast(new MyLongToByteEncoder())
                .addLast(new MyServerHandler());
    }
}
