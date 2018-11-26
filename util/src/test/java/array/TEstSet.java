package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author huqinsong
 * @date 2018/11/12
 */
public class TEstSet {

    public static void main(String[] args) {
        Set<Integer> v = new HashSet();
        v.add(1);
        v.add(2);
        v.add(2);
        v.add(2);
        v.add(3);
        // 转arr
        Integer[] arr = v.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arr));
    }
}
