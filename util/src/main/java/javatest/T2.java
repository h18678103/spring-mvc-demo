package javatest;

/**
 * @author huqinsong
 * @date 2018/12/25
 */
public class T2 {
    public static void main(String[] args) {
        boolean b = true;
        for (int i = 0; i <10 ; i++) {
            System.out.println("循环"+i);
            b = b && p(i);
        }
    }

    public static boolean p(int i){
        System.out.println(i);
        if (i==5){
            return false;
        }
        return true;
    }

}
