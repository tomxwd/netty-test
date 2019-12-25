package top.tomxwd.netty.inboundandoutboundhandler.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import top.tomxwd.netty.inboundandoutboundhandler.codec.MyByteToLongDecoder;
import top.tomxwd.netty.inboundandoutboundhandler.codec.MyLongToByteEncoder;

/**
 * @author xieweidu
 * @createDate 2019-12-25 21:21
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 出站加入一个Handler进行编码
        pipeline.addLast(new MyLongToByteEncoder())
                .addLast(new MyByteToLongDecoder())
                .addLast(new MyClientHandler());
    }
}
