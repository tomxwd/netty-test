package top.tomxwd.netty.protocoltpc.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import top.tomxwd.netty.protocoltpc.MessageProtocol;

import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xieweidu
 * @createDate 2019-12-26 22:40
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        // 接收到解码器解码后的数据并处理
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("服务端接收到信息如下：");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content, CharsetUtil.UTF_8));
        System.out.println("服务器接收到消息包数量=" + count.incrementAndGet());

        // 回复消息
        byte[] responseContent = UUID.randomUUID().toString().getBytes("utf-8");
        int responseLen = responseContent.length;
        // 构建一个协议包
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setContent(responseContent);
        messageProtocol.setLen(responseLen);
        ctx.writeAndFlush(messageProtocol);
    }
}
