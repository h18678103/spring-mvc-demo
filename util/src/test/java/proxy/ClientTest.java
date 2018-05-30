package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author huqinsong
 * @date 2018/5/22
 */
public class ClientTest {
    public static void main(String args[]) {
        SellFisher s = new ConcreteSellFisher();
        System.out.println("class="+s.getClass());
        InvocationHandler p = new ProxySellFisher(s);
        SellFisher obj = (SellFisher) Proxy.newProxyInstance(s.getClass().getClassLoader(), s.getClass().getInterfaces(), p);
        int i = obj.sellFish();
        System.out.println(i);
        i=obj.sellFish2();
        System.out.println(i);
    }
}
