import java.util.regex.Pattern;
import org.junit.Test;

/**
 * @author huqinsong
 * @date 2018/4/11
 */
public class TestIp {

    /** ip地址的正则匹配 */
    public static final String IPV4_PATTERN = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";


    private static Pattern IP_PATTERN = Pattern.compile(IPV4_PATTERN);

    public static boolean validateIp(String ip) {
        return IP_PATTERN.matcher(ip).matches();
    }

    @Test
    public void test(){
        String ip = "120.79.245.213";
        assert validateIp(ip);
    }
}
