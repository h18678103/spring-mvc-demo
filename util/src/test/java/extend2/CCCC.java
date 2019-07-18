package extend2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sky
 * @date 2019/7/18
 */
public class CCCC {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("01");
        set.add("02");
        set.add("03");
        set.add("04");
        set.add("05");
        set.add("06");

        String[] arr = new String[set.size()];
        set.toArray(arr);

        pick(arr, 4);
    }

    public static void pick(String[] arr, int n){
        for (int i = 0; i < arr.length; i++) {
            int x = i;
            for (int j = 0; j < arr.length; j++) {
                if (x >= arr.length){
                    x = 0;
                }
                System.out.print(x+"\t");
                x++;
            }
            System.out.println();
        }

    }

    public static void p(String[] arr){
        for (int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
    }
}
