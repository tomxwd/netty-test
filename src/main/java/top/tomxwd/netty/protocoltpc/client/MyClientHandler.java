package top.tomxwd.netty.protocoltpc.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import sun.plugin2.message.Message;
import top.tomxwd.netty.protocoltpc.MessageProtocol;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xieweidu
 * @createDate 2019-12-26 22:33
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        String content = new String(msg.getContent(), CharsetUtil.UTF_8);
        System.out.println("客户端接收到消息如下：");

        System.out.println("消息长度：" + len);
        System.out.println("消息内容：" + content);
        System.out.println("客户端接收消息数量：" + count.incrementAndGet());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 使用客户端发送10条 "天气冷，吃火锅" 数据
        for (int i = 0; i < 5; i++) {
            String msg = "天气冷，吃火锅";
            byte[] content = msg.getBytes(CharsetUtil.UTF_8);
            int length = content.length;
            // 创建协议包对象
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setContent(content);
            messageProtocol.setLen(length);
            ctx.writeAndFlush(messageProtocol);
        }
    }
}
