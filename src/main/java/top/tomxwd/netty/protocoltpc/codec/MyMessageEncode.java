package top.tomxwd.netty.protocoltpc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.tomxwd.netty.protocoltpc.MessageProtocol;

/**
 * @author xieweidu
 * @createDate 2019-12-28 10:11
 */
public class MyMessageEncode extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyMessageEncode.encode编码器调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
