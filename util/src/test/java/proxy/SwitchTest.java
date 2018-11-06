package proxy;

/**
 * @author huqinsong
 * @date 2018/10/9
 */
public class SwitchTest {
    public static void main(String[] args) {
        String s  = null;
        switch (s){
            case "a":{
                System.out.println("aaa");
                break;
            }
            case "b":{
                System.out.println("bbb");
                break;
            }
            default:
                System.out.println("deft");
        }
    }
}
