package top.tomxwd.netty.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 说明：
 * 1. 我们自定义一个Handler 需要继承netty规定好的某个HandlerAdapter适配器
 * 2. 这时我们自定义的Handler才能称之为一个Handler
 *
 * @author xieweidu
 * @createDate 2019-12-14 18:09
 */
//public class NettyServerHandler extends ChannelInboundHandlerAdapter {
public class NettyServerHandler extends SimpleChannelInboundHandler<StudentPOJO.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentPOJO.Student student) throws Exception {
        System.out.println("客户端发送的数据 id=" + student.getId() + " 名字=" + student.getName());
    }

    /**
     * 读取数据事件（这里我们可以读取客户端发送的数据）
     *
     * @param ctx ChannelHandlerContext：上下文对象，含有管道pipeline，通道channel，地址
     * @param msg Object：就是客户端发送的数据，默认是Object类型
     * @throws Exception
     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        // 读取从客户端发送的StudentPOJO.Student
//        StudentPOJO.Student student = (StudentPOJO.Student) msg;
//        System.out.println("客户端发送的数据 id=" + student.getId() + " 名字=" + student.getName());
//    }

    /**
     * 数据读取完毕，触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // write+flush 将数据写入到缓存，并刷新
        // 一般来讲，我们对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常，一般是需要关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
