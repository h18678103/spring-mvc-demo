import java.io.UnsupportedEncodingException;

/**
 * @author huqinsong
 * @date 2018/3/22
 */
public class T2 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "浣犲ソ"; //这是"你好"的gbk编码的字符串
        String ss = new String(s.getBytes("GBK"), "UTF-8");
        System.out.println(ss);

        String s1 = "你好";
        String ss1 = new String(s1.getBytes("UTF-8"), "GBK");
        System.out.println(ss1);
    }
}
