package bean;

import java.util.Iterator;
import java.util.Map;
import org.apache.commons.beanutils.BeanMap;

/**
 * @author huqinsong
 * @date 2019/2/14
 */
public class BeanMapTest {
    public static void main(String[] args) {
        A a = new A();
        a.setAaa("aaaa");
        a.setGGa("gga");
        a.setAbCde("AbCde");
        a.setBbB("BbB");
        BeanMap beanMap = new BeanMap(a);
        Iterator<Map.Entry<Object, Object>> entryIterator = beanMap.entryIterator();
        while (entryIterator.hasNext()){
            Map.Entry<Object, Object> entry = entryIterator.next();
            if ("class".equals(entry.getKey()) || null == entry.getValue()) {
                continue;
            }
            System.out.println(entry.getKey()+"======"+entry.getValue());
        }

    }
}