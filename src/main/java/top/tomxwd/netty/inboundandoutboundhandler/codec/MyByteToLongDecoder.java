package top.tomxwd.netty.inboundandoutboundhandler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码器byte->long
 *
 * @author xieweidu
 * @createDate 2019-12-25 21:07
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     * decode会根据接收的数据，被调用多次，直到确定没有新的元素被添加到list，或者ByteBuf没有更多的可读字节为止
     * 如果list out不为空，就会将list的内容传递给下一个ChannelInboundHandler处理，该处理器的方法也会被调用多次
     * @param ctx 上下文对象
     * @param in  入站的ByteBuf
     * @param out List集合，将解码后的数据传给下一个Handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLong decoder方法被调用");

        // 因为long是8个字节，需要判断有8个才读取
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
