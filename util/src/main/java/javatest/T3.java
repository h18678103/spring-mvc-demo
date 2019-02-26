package javatest;

/**
 * @author huqinsong
 * @date 2018/12/26
 */
public class T3 {
    public static void main(String[] args) {
        int i = 2;
        switch (i){
            case 1:
                System.out.println(1);
                return;
            case 2:
            case 3:{
                System.out.println(3);
                return;
            }

                default:
                    System.out.println(4);
        }
    }
}
