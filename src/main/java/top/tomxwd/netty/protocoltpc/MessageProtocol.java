package top.tomxwd.netty.protocoltpc;

/**
 * 协议包
 * @author xieweidu
 * @createDate 2019-12-28 10:05
 */
public class MessageProtocol {

    private int len;
    private byte[] content;

    public MessageProtocol() {
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
