package top.tomxwd.netty.simple;

import io.netty.util.NettyRuntime;
import org.junit.Test;

/**
 * @author xieweidu
 * @createDate 2019-12-14 23:41
 */
public class NettyTest {

    @Test
    public void test1(){
        System.out.println(NettyRuntime.availableProcessors());
    }

}
