package nio.test1;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author huqinsong
 * @date 2019/7/4
 */
public class TestFile {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("D:\\nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);

        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        CharArrayWriter writer = new CharArrayWriter();
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
//                writer.append((char) buf.get());
                buff.write(buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
        String s = buff.toString("GBK");
        System.out.println(s);
        String s1 = buff.toString("GBK");
        System.out.println(s1);
    }
}
