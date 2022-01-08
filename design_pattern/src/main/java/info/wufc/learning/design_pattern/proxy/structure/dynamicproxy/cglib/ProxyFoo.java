package info.wufc.learning.design_pattern.proxy.structure.dynamicproxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

/**
 * @ClassName: ProxyFoo
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 12:15
 */
public class ProxyFoo  {

    public static FooSuper create(RealFoo realFoo) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback((InvocationHandler) (o, method, objects) -> {
            System.out.println("ProxyFoo.invoke cglib begin");
            realFoo.doWork();
            System.out.println("ProxyFoo.invoke cglib end");
            return null;
        });
        enhancer.setSuperclass(RealFoo.class.getSuperclass());
        enhancer.setClassLoader(RealFoo.class.getClassLoader());
        return (FooSuper) enhancer.create();
    }
}
