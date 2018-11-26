package old;

import java.util.concurrent.Semaphore;

/**
 * @author huqinsong
 * @date 2018/3/22
 */
public class T_Semaphore {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3,true);
        semaphore.acquire();
        int i = semaphore.availablePermits();
        System.out.println(i);


        semaphore.acquire();
        i = semaphore.availablePermits();
        System.out.println(i);

        semaphore.release();
        i = semaphore.availablePermits();
        System.out.println(i);

        semaphore.acquire();
        i = semaphore.availablePermits();
        System.out.println(i);
        semaphore.acquire();
        i = semaphore.availablePermits();
        System.out.println(i);

        semaphore.release();
        i = semaphore.availablePermits();
        System.out.println(i);

        semaphore.acquire();
        i = semaphore.availablePermits();
        System.out.println(i);
//do something here

    }
}
