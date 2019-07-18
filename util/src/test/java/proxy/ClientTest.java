package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author huqinsong
 * @date 2018/5/22
 */
public class ClientTest {
    public static void main(String args[]) {
        SellFisher s = new ConcreteSellFisher();
        System.out.println("class="+s.getClass());
        SellFisher obj = (SellFisher) Proxy.newProxyInstance(
                ConcreteSellFisher.class.getClassLoader(),
                ConcreteSellFisher.class.getInterfaces(),
                (proxy, method, args1) -> {
                    System.out.println("the fish price higher!!!");
                    return (Integer) method.invoke(s, args1) + 10;
                });

        int i = obj.sellFish();
        System.out.println(i);
        i=obj.sellFish2();
        System.out.println(i);
    }
}
