/**
 * @author huqinsong
 * @date 2018/6/1
 */
public class ImplI1 implements I1 {
    public static void main(String[] args) {
        ImplI1 i = new ImplI1();
        int i1 = i.getI();
        System.out.println(i1);

        new Thread(new Runnable() { //匿名
            public void run() {
                System.out.println("runnable run");
            }
        }){
            @Override
            public void run() {
                System.out.println("subthread run");
            }

        }.start(); //结果：subthread run
    }
}
