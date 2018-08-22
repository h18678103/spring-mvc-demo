package dtdl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author huqinsong
 * @date 2018/8/22
 */
public class M {

    public static void main(String[] args) {
        T t1 = new T() {
            @Override
            public Integer add(int a, int b) {
                return a+b;
            }

            @Override
            public Integer sub(int a, int b) {
                return a-b;
            }
        };

        InvocationHandler h = (proxy, method, args1) -> {
            System.out.println("当前调用的方法是：" + method.getName());
            Object invoke = method.invoke(t1, args1[0], args1[1]);
            return invoke;
        };

        T t = (T) Proxy.newProxyInstance(T.class.getClassLoader(), new Class[]{T.class}, h);

        Integer add = t.add(1, 2);
        System.out.println(add);
        Integer sub = t.sub(1, 2);
        System.out.println(sub);

    }
}
