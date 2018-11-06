package timer;

/**
 * @author huqinsong
 * @date 2018/9/26
 */
public class TestFor {
    public static void main(String[] args) {
        a:
        for (int i = 0; i< 10; i++){
            System.out.println("1iiiiiiiii="+i);
            for (int j = 0; j < 10; j++){
                System.out.println("j="+j);
                if (j==5){
                    break;
                }
            }
            System.out.println("2iiiiiiiii="+i);
        }
    }
}
