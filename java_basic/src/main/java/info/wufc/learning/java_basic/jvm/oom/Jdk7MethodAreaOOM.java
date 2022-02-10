package info.wufc.learning.java_basic.jvm.oom;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法区溢出 仅适用JDK7
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-10 22:49
 */
public class Jdk7MethodAreaOOM {
    /**
     * vm args: -xx:permSize=10M -XX:MaxPermSize=10M
     * @param args
     */
    public static void main(final String[] args) {
        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, args);
                }
            });
            OOMObject o = (OOMObject) enhancer.create();
            o.doSomething();

        }
    }

    static class OOMObject{

        public void doSomething(){
            System.out.println("OOMObject.doSomething");
        }
    }
}
