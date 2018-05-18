package regex;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author huqinsong
 * @date 2018/5/18
 */
public class Test {

    /** 列表分隔符: ',' */
    private static final Pattern ITEM_SPLITTER = Pattern.compile("\\s*,\\s*");
    public static void main(String[] args) {
        String  s = "  ab, c , " +
                "def, " +
                "gid,      ac,a   a";
        String[] arr= ITEM_SPLITTER.split(s);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        System.out.println(Arrays.toString(arr));
    }
}
