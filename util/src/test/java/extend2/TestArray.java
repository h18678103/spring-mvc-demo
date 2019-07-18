package extend2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangyi
 * @date 2019/7/18 15:52
 */
public class TestArray {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        count2("=" ,array, 2);
    }
    public static void count2(String show ,List<Integer> array, int count) {
        --count;
        for (int i = 0; i < array.size(); i++) {
            if ( count> 0) {
                count2(show+":"+array.get(i), array.subList(i + 1, array.size()), count);
            } else {
                System.out.println(show + ":" + array.get(i));
            }
        }
    }
}