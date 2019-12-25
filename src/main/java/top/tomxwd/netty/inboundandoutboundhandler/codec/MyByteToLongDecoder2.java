package top.tomxwd.netty.inboundandoutboundhandler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author xieweidu
 * @createDate 2019-12-25 23:04
 */
public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder2 被调用");
        // 在ReplayingDecoder不需要判断是否足够读取，内部会进行判断
        out.add(in.readLong());
    }
}
