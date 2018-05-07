import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author huqinsong
 * @date 2018/4/20
 */
public class TTT {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<1000; i++){
            if (i%3==0 && i%10!=0){
                list.add(i);
            }
        }
        System.out.println("总个数："+list.size());
        Random random = new Random();
        for (int i=0; i<10; i++){
            int i1 = random.nextInt(list.size());
            System.out.println(i1);
        }
    }
}
