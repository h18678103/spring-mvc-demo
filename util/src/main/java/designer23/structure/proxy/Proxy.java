package designer23.structure.proxy;

/**
 * 代理模式（Proxy）
 * @author huqinsong
 * @date 2018/12/12
 */
interface Sourceable {
    public void method();
}
class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
public class Proxy implements Sourceable {
    private Source source;
    public Proxy(){
        super();
        this.source = new Source();
    }
    @Override
    public void method() {
        before();
        source.method();
        atfer();
    }
    private void atfer() {
        System.out.println("after proxy!");
    }
    private void before() {
        System.out.println("before proxy!");
    }

    public static void main(String[] args) {
        Sourceable source = new Proxy();
        source.method();
    }
}