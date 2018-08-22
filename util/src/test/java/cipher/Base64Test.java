package cipher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;

/**
 * @author huqinsong
 * @date 2018/7/18
 */
public class Base64Test {

    public static void main(String[] args) throws IOException {
        String s = "asdf撒的发生";
        System.out.println(s.length());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i< 20; i++){
            out.write(s.getBytes());
        }
        byte[] bytes = Base64.encodeBase64(out.toByteArray());
        System.out.println(bytes.length);
        System.out.println(new String(bytes));
        System.out.println(new String(Base64.decodeBase64(bytes)));
    }
}
