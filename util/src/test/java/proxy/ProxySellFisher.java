package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author huqinsong
 * @date 2018/5/22
 */
public class ProxySellFisher implements InvocationHandler {

    private SellFisher sell;

    public ProxySellFisher(SellFisher sell) {
        this.sell = sell;
    }

    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("the fish price higher!!!");
        return (Integer) method.invoke(sell, args) + 10;
    }
}
