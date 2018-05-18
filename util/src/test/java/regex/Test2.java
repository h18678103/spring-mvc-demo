package regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huqinsong
 * @date 2018/5/18
 */
public class Test2 {

    /** 列表分隔符: ',' */
    private static final Pattern ITEM_SPLITTER = Pattern.compile("\\s*,\\s*");
    public static void main(String[] args) {
        String  s = "  ab, c , " +
                "def, " +
                "gid,      ac,a   a";
        Matcher matcher = ITEM_SPLITTER.matcher(s);
        List<String> list = new ArrayList<>();

        while (matcher.find()){
            list.add("abc");
        }
        String[] arr = new String[list.size()];
        list.toArray(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        System.out.println(Arrays.toString(arr));
    }
}
