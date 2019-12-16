package top.tomxwd.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author xieweidu
 * @createDate 2019-12-16 21:55
 */
public class NettyByteBuf02 {

    public static void main(String[] args) {
        // 创建ByteBuf
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,北京", CharsetUtil.UTF_8);
        // 使用相关的API
        if(byteBuf.hasArray()){//true
            byte[] content = byteBuf.array();
            // 将content转为字符串
            System.out.println("new String(content,CharsetUtil.UTF_8) = " + new String(content, CharsetUtil.UTF_8));
            System.out.println("byteBuf = " + byteBuf);
            // 数组偏移量
            System.out.println("byteBuf.arrayOffset() = " + byteBuf.arrayOffset());
            System.out.println("byteBuf.readerIndex() = " + byteBuf.readerIndex());
            System.out.println("byteBuf.writerIndex() = " + byteBuf.writerIndex());
            System.out.println("byteBuf.capacity() = " + byteBuf.capacity());
            // 读取一个字节 getByte不会影响readerIndex，所以用readByte
            byteBuf.readByte();
            // 可读取的字节数量
            System.out.println("byteBuf.readableBytes() = " + byteBuf.readableBytes());
            // 使用for取出各个字节
            for (int i = 0; i < byteBuf.readableBytes(); i++) {
                System.out.println((char)byteBuf.getByte(i));
            }
            // 指定开始位置以及读取长度 读取
            System.out.println("byteBuf.getCharSequence(1,4, Charset.forName(\"utf-8\")) = " + byteBuf.getCharSequence(1, 4, Charset.forName("utf-8")));

        }
    }

}
