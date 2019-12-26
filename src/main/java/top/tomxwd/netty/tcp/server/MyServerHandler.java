package top.tomxwd.netty.tcp.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xieweidu
 * @createDate 2019-12-26 22:40
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        // msg->byteArr
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        // 将buffer转字符串
        String msgStr = new String(buffer, Charset.forName("utf-8"));
        System.out.println("服务器接收到数据：" + msgStr);
        System.out.println("服务器接收到消息量 = " + count.incrementAndGet());
        // 服务器回送数据给客户端，回送随机id
        ByteBuf responseByteBuf = Unpooled.copiedBuffer(UUID.randomUUID().toString(), Charset.forName("utf-8"));
        ctx.writeAndFlush(responseByteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
