package exted;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huqinsong
 * @date 2019/3/27
 */
public class T {

    /** 单词占位符：${xxx} */
    private static final Pattern WORD_PLACEHOLDER = Pattern.compile("\\$\\{(\\w+)}");

    public static void main(String[] args) {
        String s = "abcd${aaa}efg${bbbb}hijfg${ccccc}hij";
        Matcher matcher = WORD_PLACEHOLDER.matcher(s);
        while (matcher.find()){
            for (int j = 0; j <= 1; j++) {
                System.out.println(j+"="+matcher.group(j));

            }
        }
    }
}
