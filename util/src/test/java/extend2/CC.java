package extend2;

import java.util.HashSet;
import java.util.Set;

public class CC {

    static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) {

        for (Integer i : set){
            System.out.println(i);
        }
    }

    static {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(6);
        set.add(7);
        set.add(8);
    }
}
