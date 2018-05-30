package try1;

/**
 * @author huqinsong
 * @date 2018/5/22
 */
public class TryTest {
    public static void main(String[] args) {
        try {
            System.out.println(1);
            System.exit(0);
            return;
        } catch (Exception e){
            System.out.println(2);
        } finally {
            System.out.println(3);
        }
    }
}
