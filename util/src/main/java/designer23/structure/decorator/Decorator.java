package designer23.structure.decorator;

/**
 * 装饰模式（Decorator）
 * @author huqinsong
 * @date 2018/12/12
 */

interface Sourceable {
    void method();
}

class Source implements Sourceable {

    @Override
    public void method() {
        System.out.println("the original method!");
    }
}

public class Decorator implements Sourceable {

    private Sourceable source;

    public Decorator(Sourceable source){
        super();
        this.source = source;
    }
    @Override
    public void method() {
        System.out.println("before decorator!");
        source.method();
        System.out.println("after decorator!");
    }

    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable obj = new Decorator(source);
        obj.method();
    }
}
