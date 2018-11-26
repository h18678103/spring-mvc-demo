package spring;

/**
 * @author huqinsong
 * @date 2018/11/12
 */

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 每个bean初始化调用一次
 * 注意,不要返回null,否则从容器中获取不到对象
 * 作用:
 * 做一些回调之类的
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * bean的依赖装配之后触发的(属性设置之后触发的)
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization------" + bean.getClass() + "==>beanName==>"+beanName);
        return bean;
    }


    /**
     * 在bean 的 init方法之后触发的.在bean的属性设置之后.
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization------" + bean.getClass() + "==>beanName==>"+beanName);
        return bean;
    }
}
