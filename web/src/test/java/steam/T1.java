package steam;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huqinsong
 * @date 2019/3/20
 */
public class T1 {
    List<String> wordList;

    @Before
    public void init() {
        wordList = new ArrayList<String>() {
            {
                add("a");
                add("a");
                add("b");
                add("c");
                add("d");
                add("e");
                add("f");
                add("g");
            }
        };
    }
    /**
     * 延迟执行特性，在聚合操作之前都可以添加相应元素
     */
    @Test
    public void test() {
        Stream<String> words = wordList.stream();
        wordList.add("END");
//        long n = words.distinct().count();
//        System.out.println(n);

        words.distinct().forEach(System.out::println);

    }

    private static String fetchIp(String ip) {
        String[] split = ip.split(",");
        return split[0];
    }

    public static void main(String[] args) {
        String appName = "appName";
        String userId = "userId";
        String secretKey = "secretKey";
        String format = MessageFormat.format("otpauth://totp/{0}({1})?secret={2}&issuer={0}", appName, userId, secretKey);
        System.out.println(format);
    }

}
