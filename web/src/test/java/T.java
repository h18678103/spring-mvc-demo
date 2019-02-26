import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author huqinsong
 * @date 2019/1/3
 */
public class T {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "13800000001%23%e4%b8%8b%e5%8f%91%e5%86%85%e5%ae%b91%0d13800000002%23%e4%b8%8b%e5%8f%91%e5%86%85%e5%ae%b92%0d13800000003%23%e4%b8%8b%e5%8f%91%e5%86%85%e5%ae%b93%0d13800000004%23%e4%b8%8b%e5%8f%91%e5%86%85%e5%ae%b94%0d";
        String decode = URLDecoder.decode(s, "UTF-8");
        System.out.println(decode);
    }
}
