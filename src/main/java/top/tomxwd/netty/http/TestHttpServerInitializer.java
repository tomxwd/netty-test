package top.tomxwd.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author xieweidu
 * @createDate 2019-12-15 11:51
 */
public class TestHttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 向管道加入处理器

        // 得到管道
        ChannelPipeline pipeline = ch.pipeline();

        // 加入一个netty提供的HttpServerCodec codec => [coder  -  decoder]
        /**
         * HttpServerCodec说明：
         *  1. HttpServerCodec是netty提供的一个处理http的编码解码器
         */
        pipeline.addLast("MyHttpServerCode",new HttpServerCodec());
        //  2. 增加一个自定义的handler
        pipeline.addLast("MyTestHttpServerHandler",new TestHttpServerHandler());
    }
}
