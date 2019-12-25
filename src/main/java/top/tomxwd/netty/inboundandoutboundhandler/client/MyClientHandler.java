package top.tomxwd.netty.inboundandoutboundhandler.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author xieweidu
 * @createDate 2019-12-25 21:27
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {

        System.out.println("服务器的ip：" + ctx.channel().remoteAddress());
        System.out.println("收到服务器消息：" + msg);

    }

    /**
     * 重写channelActive发送消息
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler发送数据");
        // 发送long
        ctx.writeAndFlush(123456L);

        /**
         * 分析：
         * 1. abcdabcdabcdabcd有16个字节，发送的时候会以8个为一组
         * 2. 该处理器的后一个handler是MyLongToByteEncoder
         * 3. 父类MessageToByteEncoder有个write方法：
         *     @Override
         *     public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
         *         ByteBuf buf = null;
         *         try {
         *             if (acceptOutboundMessage(msg)) {
         *                 @SuppressWarnings("unchecked")
         *                 I cast = (I) msg;
         *                 buf = allocateBuffer(ctx, cast, preferDirect);
         *                 try {
         *                     encode(ctx, cast, buf);
         *                 } finally {
         *                     ReferenceCountUtil.release(cast);
         *                 }
         *
         *                 if (buf.isReadable()) {
         *                     ctx.write(buf, promise);
         *                 } else {
         *                     buf.release();
         *                     ctx.write(Unpooled.EMPTY_BUFFER, promise);
         *                 }
         *                 buf = null;
         *             } else {
         *                 ctx.write(msg, promise);
         *             }
         *         } catch (EncoderException e) {
         *             throw e;
         *         } catch (Throwable e) {
         *             throw new EncoderException(e);
         *         } finally {
         *             if (buf != null) {
         *                 buf.release();
         *             }
         *         }
         *     }
         * 判断了如果类型匹配不上，就不做处理直接write
         * 4. 因此编写Encoder的时候要注意传入的数据类型和处理的数据类型要一致
         */
//        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));
    }
}
