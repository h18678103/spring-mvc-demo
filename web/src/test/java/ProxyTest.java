/**
 * @author huqinsong
 * @date 2019/1/8
 */
public class ProxyTest {

    public static void main(String[] args) {
        LogHandler logHandler=new LogHandler();
        ProxyIt o = (ProxyIt) logHandler.newProxyInstance(new ProxyItImpl());
        o.p(12);

    }
}
