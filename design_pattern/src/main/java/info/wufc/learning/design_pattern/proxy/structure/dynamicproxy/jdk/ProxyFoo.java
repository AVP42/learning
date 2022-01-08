package info.wufc.learning.design_pattern.proxy.structure.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: ProxyFoo
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 12:15
 */
public class ProxyFoo implements InvocationHandler {

    private RealFoo realFoo;

    private ProxyFoo(RealFoo realFoo) {
        this.realFoo = realFoo;
    }

    public static FooInterface create(RealFoo realFoo) {
        return (FooInterface) Proxy.newProxyInstance(realFoo.getClass().getClassLoader(),
                realFoo.getClass().getInterfaces(), new ProxyFoo(realFoo));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ProxyFoo.invoke begin");
        Object invoke = method.invoke(realFoo, args);
        System.out.println("ProxyFoo.invoke end");
        return null;
    }
}
