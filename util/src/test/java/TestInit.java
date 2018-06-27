/**
 * @author huqinsong
 * @date 2018/6/1
 */
public class TestInit {
    C c;

    public TestInit(C c) {
        this.c = c;
    }

    public void pp(){
        c.p();
    }

    public static void main(String[] args) {
        new TestInit(new C("111")){
            @Override
            public void pp() {
                System.out.println("222");
            }
        }.pp();
    }

}


class C {
    String s;

    public C(String s) {
        this.s = s;
    }

    public void p(){
        System.out.println("s="+s);
    }
}