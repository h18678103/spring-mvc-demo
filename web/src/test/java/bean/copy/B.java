package bean.copy;

/**
 * @author huqinsong
 * @date 2019/5/16
 */
public class B {
    String a1;
    String a2;
    C c;

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    @Override
    public String toString() {
        return "B{" +
                "a1='" + a1 + '\'' +
                ", a2='" + a2 + '\'' +
                ", c=" + c +
                '}';
    }
}
