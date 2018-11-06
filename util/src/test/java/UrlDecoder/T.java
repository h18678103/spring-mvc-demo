package UrlDecoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author huqinsong
 * @date 2018/10/19
 */
public class T {
    /**
     * 金额格式：12位固定数字，左端不足补0
     */
    private static final String MONEY_FORMAT = "%012d";
    public static void main(String[] args) throws UnsupportedEncodingException {
        String asdfsdaf = URLDecoder.decode("asdfsdaf", "utf-8");
        System.out.println(asdfsdaf);

        String format = String.format(MONEY_FORMAT, 12);
        System.out.println(format);
    }
}
