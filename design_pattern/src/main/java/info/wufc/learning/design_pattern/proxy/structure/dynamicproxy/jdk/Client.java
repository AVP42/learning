package info.wufc.learning.design_pattern.proxy.structure.dynamicproxy.jdk;

/**
 * @ClassName: Cleint
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 12:23
 */
public class Client  {
    public static void main(String[] args) throws Throwable {
        FooInterface fooInterface = ProxyFoo.create(new RealFoo());
        fooInterface.doWork();
    }
}
