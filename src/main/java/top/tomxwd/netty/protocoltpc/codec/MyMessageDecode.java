package top.tomxwd.netty.protocoltpc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import top.tomxwd.netty.protocoltpc.MessageProtocol;

import java.util.List;

/**
 * @author xieweidu
 * @createDate 2019-12-28 10:13
 */
public class MyMessageDecode extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecode.decode解码调用");
        // 字节码->MessageProtocol
        int len = in.readInt();
        byte[] content = new byte[len];
        in.readBytes(content);
        // 封装成MessageProtocol对象，放入out传递给下一个handler处理
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(len);
        messageProtocol.setContent(content);
        out.add(messageProtocol);
    }
}
