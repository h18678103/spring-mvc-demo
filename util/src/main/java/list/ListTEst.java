package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huqinsong
 * @date 2018/11/22
 */
public class ListTEst {

    public static void main(String[] args) {
        List<Integer> l = new ArrayList();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        l.add(6);

        l.removeIf(o -> o<5);

        System.out.println(Arrays.toString(l.toArray()));

    }
}
