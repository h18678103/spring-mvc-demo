import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author huqinsong
 * @date 2018/4/18
 */
public class TT {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<1000; i++){
            if (i%3==0 && i%10!=0){
                list.add(i);
            }
        }
        System.out.println("满足条件的数的个数："+list.size());
        Random random = new Random();
        System.out.println("100个随机数：");
        for (int i=0; i<100 && i<list.size(); ){
            System.out.print(list.get(random.nextInt(list.size()))+"\t");
            i++;
            if (i!=0 && i%10==0){
                System.out.println();
            }
        }
    }
}
