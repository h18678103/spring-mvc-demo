package excptest;

/**
 * @author sky
 * @date 2019/7/16
 */
public class T {
    public static void main(String[] args)  {
        T t = new T();
        t.t1();
    }

    public void t1(){
        System.out.println(1);
        throw new Ex1();
    }
}
