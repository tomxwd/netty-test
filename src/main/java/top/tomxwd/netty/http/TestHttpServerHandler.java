package top.tomxwd.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 说明：
 * 1. SimpleChannelInboundHandler是ChannelInboundHandlerAdapter的子类
 * 2. HttpObject表示客户端和服务器端相互通讯的数据被封装成HttpObject类型
 *
 * @author xieweidu
 * @createDate 2019-12-15 11:51
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 当有读取事件的时候，就会触发
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 判断msg是不是HttpRequest类型
        if (msg instanceof HttpRequest) {
            System.out.println("pipeline hashcode " + ctx.pipeline().hashCode() + " TestHttpServerHandler hashcode " + this.hashCode());
            System.out.println("msg 类型 = " + msg.getClass());
            System.out.println("客户端地址 " + ctx.channel().remoteAddress());

            // 获取到msg的信息
            HttpRequest httpRequest = (HttpRequest) msg;
            // 获取uri，过滤特定资源
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("你请求了favicon.ico,不做响应！");
                return;
            }


            // 回复信息给浏览器[Http协议]
            ByteBuf content = Unpooled.copiedBuffer("hello，我是服务器", CharsetUtil.UTF_8);
            // 构造一个Http的响应，即HttpResponse
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers()
                    .set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8")
                    .set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            // 将构建好的response返回
            ctx.writeAndFlush(response);
        }
    }
}
