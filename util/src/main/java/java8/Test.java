package java8;

import java.util.Base64;

/**
 * @author huqinsong
 * @date 2018/11/13
 */
public class Test {


    Base64 base64;
    static volatile boolean start = true;
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("aaaaaaaaaaa"+start);
                    Thread.sleep(1000);
                    System.out.println("bbbbbbb");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    start = false;
                    System.out.println("ccccccccccc"+start);
                }
            }
        }.start();
        System.out.println("dddddddddddddddd"+start);
        int n = 0;
        while (true){
            //System.out.println("eeeeeeeeeeeeeeeeeeeee"+start); 1116850488
            //System.out.println("eeeeeeeeeeeeeeeeeeeee"+start); 1647715741
            //System.out.println("eeeeeeeeeeeeeeeeeeeee"+start); 1622436541
            //System.out.println("eeeeeeeeeeeeeeeeeeeee"+start); 1562321636
            if (start==false){
                break;
            }
            n++;
        }
        System.out.println("n===="+n);
    }
}
