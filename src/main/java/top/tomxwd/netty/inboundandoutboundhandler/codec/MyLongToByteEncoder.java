package top.tomxwd.netty.inboundandoutboundhandler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 * @author xieweidu
 * @createDate 2019-12-25 21:23
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    /**
     * 编码方法
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyLongToByteEncoder encode方法被调用");
        System.out.println("msg=" + msg);
        out.writeLong(msg);
    }
}
