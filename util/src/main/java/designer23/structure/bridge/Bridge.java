package designer23.structure.bridge;

/**
 * 桥接模式（Bridge）
 * @author huqinsong
 * @date 2018/12/12
 */
interface Sourceable {
    void method();
}
class SourceSub1 implements Sourceable {
    @Override
    public void method() {
        System.out.println("this is the first sub!");
    }
}
class SourceSub2 implements Sourceable {
    @Override
    public void method() {
        System.out.println("this is the second sub!");
    }
}
public class Bridge {

    private Sourceable source;

    public void method(){
        source.method();
    }

    public void setSource(Sourceable source) {
        this.source = source;
    }
}

class BridgeTest {

    public static void main(String[] args) {

        Bridge bridge = new Bridge();

        /*调用第一个对象*/
        Sourceable source1 = new SourceSub1();
        bridge.setSource(source1);
        bridge.method();

        /*调用第二个对象*/
        Sourceable source2 = new SourceSub2();
        bridge.setSource(source2);
        bridge.method();
    }
}